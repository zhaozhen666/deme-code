package com.zhao.rpc.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class RpcDecoder extends ByteToMessageDecoder {

    private Class<?> genericClass;
    public RpcDecoder(Class<?> genericClass){
        this.genericClass =genericClass;
    }
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {
        if (in.readableBytes()<4){
            return;
        }
        in.markReaderIndex();
        int dataLength = in.readInt();
        if (in.readableBytes()<dataLength){
            in.resetReaderIndex();
            return;
        }

        byte [] data = new byte[dataLength];
        in.readBytes(data);
        Object o =Serialization.deserialize(data,genericClass);
        list.add(o);
    }
}
