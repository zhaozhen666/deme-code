package com.zhao.rpc.proxy;

import com.zhao.rpc.client.RpcFuture;

public interface RpcAsyncProxy {

    RpcFuture call(String funcName,Object ... args);
}
