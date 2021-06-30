package com.zhao.rpc.client;

public interface RpcCallBack {

    void success(Object result);


    void failure(Throwable cause);
}
