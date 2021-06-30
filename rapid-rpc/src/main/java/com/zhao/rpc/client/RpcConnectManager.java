package com.zhao.rpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import javax.naming.ldap.SortKey;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class RpcConnectManager {

    private static  volatile  RpcConnectManager RPC_CONNECT_MANAGER=new RpcConnectManager();

    private Map<InetSocketAddress,RpcClientHandler> connectHandlerMap = new ConcurrentHashMap<>();
    private CopyOnWriteArrayList<RpcClientHandler> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

    private ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(16,16,60, TimeUnit.SECONDS,new ArrayBlockingQueue<>(65536));

    private EventLoopGroup eventLoopGroup = new NioEventLoopGroup(4);

    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition connectedCondition = reentrantLock.newCondition();
    private long waitMillSeconds =6000;

    private volatile boolean isRunning = true;
    private volatile AtomicInteger handlerIndx = new AtomicInteger(0);
    private RpcConnectManager(){

    }

    public static RpcConnectManager getInstance(){
        return RPC_CONNECT_MANAGER;
    }


    public void  connect(String serverAddresses){
        List<String> list = Arrays.asList(serverAddresses.split(","));

    }

    public void  updateConnectedServer(List<String> servers){
        if (CollectionUtils.isNotEmpty(servers)){
            HashSet<InetSocketAddress> newSocketAddress = new HashSet<>();
            for (int i =0;i<servers.size();i++ ){
                String server = servers.get(i);
                String serverPort[] = server.split(":");
                if (serverPort.length==2){
                    String host =serverPort[0];
                    int port =Integer.valueOf(serverPort[1]);
                    final InetSocketAddress remoteAddr = new InetSocketAddress(host,port);
                    newSocketAddress.add(remoteAddr);
                }else {
                    log.error("server format error");
                }
            }
            for (InetSocketAddress inetSocketAddress :newSocketAddress){
                if (!connectHandlerMap.keySet().contains(inetSocketAddress)){
                    connectAsync(inetSocketAddress);
                }
            }

            //todo  newSocketAddress中不存在的地址要移除
            for (int i=0;i<copyOnWriteArrayList.size();i++){
                    RpcClientHandler rpcClientHandler = copyOnWriteArrayList.get(i);
                    SocketAddress socketAddress = rpcClientHandler.getRemoteAddr();
                    if (!newSocketAddress.contains(socketAddress)){
                        log.info("remove invalid node "+socketAddress);
                        RpcClientHandler handler = connectHandlerMap.get(socketAddress);
                        if (handler!=null){
                            handler.close();
                            connectHandlerMap.remove(socketAddress);

                        }
                        copyOnWriteArrayList.remove(rpcClientHandler);
                    }

            }
        }else {
            log.error("server is empty");
            clearConnected();
        }
    }

    private void connectAsync(InetSocketAddress inetSocketAddress) {
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                Bootstrap b = new Bootstrap();
                b.group(eventLoopGroup)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY,true)
                        .handler(new RpcClientInitializer());

                        connect(b,inetSocketAddress);
            }
        });
    }

    private void connect(final Bootstrap bootstrap,InetSocketAddress inetSocketAddress){
        final ChannelFuture channelFuture = bootstrap.connect(inetSocketAddress);
        //失败时如何监听
        channelFuture.channel().closeFuture().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                log.info("channle connect fail remote"+inetSocketAddress);
                //重连
                channelFuture.channel().eventLoop().schedule(new Runnable() {
                    @Override
                    public void run() {
                            clearConnected();
                    }
                },3,TimeUnit.SECONDS);
            }
        });

        //成功时监听
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()){
                        log.info("success connect to remote "+inetSocketAddress);
                        RpcClientHandler handler =channelFuture.channel().pipeline().get(RpcClientHandler.class);
                        addHandler(handler);
                    }
            }
        });
    }

    private void addHandler(RpcClientHandler handler) {
        copyOnWriteArrayList.add(handler);
        InetSocketAddress inetSocketAddress = (InetSocketAddress) handler.getRemoteAddr();
        connectHandlerMap.put(inetSocketAddress,handler);
        //通知业务处理
        signalAvailableHandler();
    }

    private void signalAvailableHandler() {
                reentrantLock.lock();
                try {
                        connectedCondition.signalAll();
                }finally {
                    reentrantLock.unlock();
                }

    }
    private boolean waitingAvailableHandler() throws InterruptedException {
        reentrantLock.lock();
        try {
           return connectedCondition.await(this.waitMillSeconds,TimeUnit.MILLISECONDS);
        }finally {
            reentrantLock.unlock();
        }

    }

    public RpcClientHandler chooseHandler(){
        CopyOnWriteArrayList<RpcClientHandler> handlers = (CopyOnWriteArrayList<RpcClientHandler>) this.copyOnWriteArrayList.clone();
        int size = handlers.size();
        while (isRunning&&size<=0){
            try {
                boolean available = waitingAvailableHandler();
                if (available){
                    handlers = (CopyOnWriteArrayList<RpcClientHandler>) this.copyOnWriteArrayList.clone();
                    size=handlers.size();
                }

            }catch (InterruptedException e ){
                    log.error("no wait server node ");
                    throw new RuntimeException("");
            }
        }
        if (!isRunning){
            return  null;
        }
        return  handlers.get((handlerIndx.getAndAdd(1)+size)%size);
    }

    public void stop(){
        isRunning =false;
        for (int i=0;i<copyOnWriteArrayList.size();i++){
            RpcClientHandler rpcClientHandler = copyOnWriteArrayList.get(i);
            rpcClientHandler.close();
        }
        signalAvailableHandler();
        threadPoolExecutor.shutdown();
        eventLoopGroup.shutdownGracefully();

    }

    public void reconnect(final RpcClientHandler rpcClientHandler,final SocketAddress socketAddress){
        if (rpcClientHandler!=null){
            rpcClientHandler.close();
            copyOnWriteArrayList.remove(socketAddress);
            connectHandlerMap.remove(socketAddress);
        }
        connectAsync((InetSocketAddress) socketAddress);

    }

    private void clearConnected(){
            for (RpcClientHandler rpcClientHandler:copyOnWriteArrayList){
                SocketAddress socketAddress =rpcClientHandler.getRemoteAddr();
                RpcClientHandler handler = connectHandlerMap.get(socketAddress);
                if (handler!=null){
                    handler.close();
                    connectHandlerMap.remove(socketAddress);
                }

            }
            copyOnWriteArrayList.clear();
    }

}
