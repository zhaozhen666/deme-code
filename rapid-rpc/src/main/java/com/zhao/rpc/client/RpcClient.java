package com.zhao.rpc.client;

import com.zhao.rpc.proxy.RpcAsyncProxy;
import com.zhao.rpc.proxy.RpcProxyImpl;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RpcClient {

    private String serverAddress;
    private  long timeout;
    private Map<Class<?>,Object> syncProxyInstanceMap = new ConcurrentHashMap<>();
    private Map<Class<?>,Object> asyncProxyInstanceMap = new ConcurrentHashMap<>();

    public RpcClient(String serverAddress, long timeout) {
        this.serverAddress = serverAddress;
        this.timeout = timeout;
    }

    private  void connect(){
        RpcConnectManager.getInstance().connect(serverAddress);
    }

    private void stop(){
        RpcConnectManager.getInstance().stop();
    }

    public <T> T invokeSync(Class<T> interfaceClass){
        if (syncProxyInstanceMap.containsKey(interfaceClass)){
            return (T)syncProxyInstanceMap.get(interfaceClass);
        }else {
            Object object = Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                    new Class<?>[]{interfaceClass},
                    new RpcProxyImpl<>(interfaceClass,timeout));
            syncProxyInstanceMap.put(interfaceClass,object);
            return (T)object;
        }

    }

    public <T> RpcAsyncProxy invokeAsync(Class<T> interfaceClass){
        if (asyncProxyInstanceMap.containsKey(interfaceClass)){
            return (RpcAsyncProxy) asyncProxyInstanceMap.get(interfaceClass);
        }else {
            RpcProxyImpl<T> asyncProxyInstance = new RpcProxyImpl<>(interfaceClass,timeout);
            asyncProxyInstanceMap.put(interfaceClass,asyncProxyInstance);
            return asyncProxyInstance;
        }

    }
}
