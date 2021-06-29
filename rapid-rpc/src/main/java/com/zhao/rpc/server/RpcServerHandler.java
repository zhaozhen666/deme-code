package com.zhao.rpc.server;

import com.zhao.rpc.codec.RpcRequest;
import com.zhao.rpc.codec.RpcResponse;
import io.netty.channel.*;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RpcServerHandler extends SimpleChannelInboundHandler<RpcRequest> {
    private Map<String,Object> handlerMap;

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(16,16,1000, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(65536));

    public RpcServerHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest rpcRequest) throws Exception {
                threadPoolExecutor.submit(new Runnable() {
                    @Override
                    public void run() {
                        RpcResponse response = new RpcResponse();
                        response.setRequestId(rpcRequest.getRequestId());
                        try {
                            Object result = handle(rpcRequest);
                            response.setResult(result);
                        }catch (Throwable t){
                            response.setThrowable(t);
                            log.error("service throw "+t);
                        }
                        ctx.writeAndFlush(response).addListener(new ChannelFutureListener() {
                            @Override
                            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                                if (channelFuture.isSuccess()){

                                }
                            }
                        });
                    }
                });
    }

    private Object handle(RpcRequest request) throws InvocationTargetException {
            String className  = request.getClassName();
            Object servcieRef = handlerMap.get(className);
            Class<?> serviceClass = servcieRef.getClass();
            String methodName = request.getMethodName();
            Class<?>[] paramterTypes = request.getParamterTypes();
            Object[] paramters = request.getParamters();

            FastClass fastClass = FastClass.create(serviceClass);
            FastMethod fastMethod = fastClass.getMethod(methodName,paramterTypes);
            return fastMethod.invoke(servcieRef,paramters);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("server caught throwable"+ctx);
        ctx.close();
    }
}
