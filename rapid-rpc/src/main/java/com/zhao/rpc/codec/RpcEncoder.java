package com.zhao.rpc.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class RpcEncoder extends MessageToByteEncoder<Object> {
    private Class<?> genericClass;

    public RpcEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf out) throws Exception {
        if (genericClass.isInstance(o)) {
            byte[] msgData = Serialization.serialize(o);
            //包头
            out.writeInt(msgData.length);
            //包体
            out.writeBytes(msgData);
        }
    }
}
