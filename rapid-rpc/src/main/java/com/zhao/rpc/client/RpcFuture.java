package com.zhao.rpc.client;

import com.zhao.rpc.codec.RpcRequest;
import com.zhao.rpc.codec.RpcResponse;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class RpcFuture implements Future<Object> {

    private RpcRequest request;
    private RpcResponse response;
    private long startTime;
    private static final long  TIME_LIMIT = 5000;

    private List<RpcCallBack> pendingCallBack = new ArrayList<>();
    private ReentrantLock lock = new ReentrantLock();
    private Sync sync;

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(16,16,60,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(65536));
    public RpcFuture(RpcRequest request) {
        this.request = request;
        this.startTime = System.currentTimeMillis();
        this.sync = new Sync();
    }

    public void done(RpcResponse response) {

        this.response =response;
        boolean success = sync.release(1);
        if (success){
            invokeCallBacks();
        }
        long costTime = System.currentTimeMillis()-startTime;
        if (costTime>TIME_LIMIT){
            log.warn("time too long");
        }
    }

    private void invokeCallBacks() {
        lock.lock();
        try {
            for (RpcCallBack rpcCallBack :pendingCallBack){
                runCallBack(rpcCallBack);
            }
        }finally {
            lock.unlock();
        }

    }

    private void runCallBack(RpcCallBack rpcCallBack) {
        final RpcResponse response = this.response;

        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                if (response.getThrowable()==null){
                    rpcCallBack.success(response.getResult());
                }else {
                    rpcCallBack.failure(response.getThrowable());
                }
            }
        });
    }


    public RpcFuture addCallBack(RpcCallBack callBack){
        lock.lock();
        try {
            if (isDone()){
                runCallBack(callBack);
            }else {
                this.pendingCallBack.add(callBack);
            }
        }finally {
            lock.unlock();
        }
        return this;
    }
    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
         throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCancelled() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isDone() {
        return sync.isDone();
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        sync.acquire(-1);
        if (this.response!=null){
            return this.response.getResult();
        }
            return null;

    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        boolean success = sync.tryAcquireNanos(-1,unit.toNanos(timeout));
        if (success){
            if (this.response!=null){
                return this.response.getResult();
            }
            return null;
        }else {
            throw new RuntimeException(" timeout exception requestId "+this.request.getRequestId()
                    +" className "+this.request.getClassName()
                    +" methodName "+this.request.getMethodName());
        }
    }

    class Sync extends AbstractQueuedSynchronizer {
        private int done =1;
        private int pending =0;


        @Override
        protected boolean tryAcquire(int acquires) {
            return getState()==done? true:false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (getState()==pending){
                if (compareAndSetState(pending,done)){
                    return true;
                }
            }
            return false;
        }

        public Boolean isDone(){
            return getState()==done;
        }
    }
}
