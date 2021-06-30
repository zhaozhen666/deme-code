package com.zhao.rpc.client;

import com.zhao.rpc.codec.RpcRequest;
import com.zhao.rpc.codec.RpcResponse;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.Data;

import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;
public class RpcClientHandler extends SimpleChannelInboundHandler<RpcResponse> {

    private Channel channel;
    private SocketAddress remoteAddr;

    private Map<String,RpcFuture> pendingResultTable = new HashMap<>();
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.remoteAddr = this.channel.remoteAddress();

    }
    public Channel getChannel() {
        return channel;
    }
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        this.channel=ctx.channel();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcResponse rpcResponse) throws Exception {
                String requestId = rpcResponse.getRequestId();
                RpcFuture rpcFuture = pendingResultTable.get(requestId);
                if (rpcFuture!=null){
                    pendingResultTable.remove(requestId);
                    rpcFuture.done(rpcResponse);
                }
    }

    public SocketAddress getRemoteAddr(){
        return this.remoteAddr;
    }

    public void close() {
        channel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    public RpcFuture sendRequest(RpcRequest request){
            RpcFuture rpcFuture = new RpcFuture(request);
            pendingResultTable.put(request.getRequestId(),rpcFuture);
            channel.writeAndFlush(request);
            return rpcFuture;

    }
}
