package com.zhao.rpc.server;

import com.zhao.rpc.codec.RpcDecoder;
import com.zhao.rpc.codec.RpcEncoder;
import com.zhao.rpc.codec.RpcRequest;
import com.zhao.rpc.codec.RpcResponse;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class RpcServer {
    private  String serverAddr;
    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    private EventLoopGroup workGroup = new NioEventLoopGroup();

    public RpcServer(String serverAddr) throws InterruptedException {
        this.serverAddr = serverAddr;
        this.start();
    }

    public void start() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline cp = socketChannel.pipeline();
                        cp.addLast(new LengthFieldBasedFrameDecoder(65536,0,4,0,0));
                        cp.addLast(new RpcDecoder(RpcRequest.class));
                        cp.addLast(new RpcEncoder(RpcResponse.class));
                        cp.addLast(new RpcServerHandler());
                    }
                });

        String[] array = serverAddr.split(":");
        String host = array[0];
        Integer port = Integer.valueOf(array[1]);
        ChannelFuture channelFuture = bootstrap.bind(host,port).sync();
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()){
                    log.info("server success bind to"+serverAddr);
                }else {
                    log.error("server fail bind to"+serverAddr);
                    throw new RuntimeException("server fail");
                }
            }
        });
        channelFuture.await(5000, TimeUnit.MILLISECONDS);
        if (channelFuture.isSuccess()){
            log.info("rpc server start success");
        }

    }

    public void registerProcessor() {

    }

    public void close(){
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
    }
}
