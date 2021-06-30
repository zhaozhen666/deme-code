package com.zhao.rpc.proxy;

import com.zhao.rpc.client.RpcClientHandler;
import com.zhao.rpc.client.RpcConnectManager;
import com.zhao.rpc.client.RpcFuture;
import com.zhao.rpc.codec.RpcRequest;
import com.zhao.rpc.codec.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RpcProxyImpl<T> implements InvocationHandler,RpcAsyncProxy {

    private Class<T> clazz;
    private long timeout;
    public RpcProxyImpl(Class<T> interfaceClass, long timeout) {
        this.clazz=interfaceClass;
        this.timeout =timeout;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = new RpcRequest();
        request.setRequestId(UUID.randomUUID().toString());
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParamterTypes(method.getParameterTypes());
        request.setParamters(args);
        RpcClientHandler handler = RpcConnectManager.getInstance().chooseHandler();
        RpcFuture future =handler.sendRequest(request);

        return future.get(timeout, TimeUnit.SECONDS);
    }

    @Override
    public RpcFuture call(String funcName, Object... args) {
        RpcRequest request = new RpcRequest();
        request.setRequestId(UUID.randomUUID().toString());
        request.setMethodName(funcName);
        request.setClassName(this.clazz.getName());
        request.setParamters(args);
        Class<?> [] paramterTypes = new Class[args.length];
        for (int i=0;i<args.length;i++){
            paramterTypes[i] = getClassType(args[i]);

        }
        request.setParamterTypes(paramterTypes);
        RpcClientHandler clientHandler = RpcConnectManager.getInstance().chooseHandler();
       RpcFuture future= clientHandler.sendRequest(request);
        return future;
    }

    private Class<?> getClassType(Object obj) {
        Class<?> classType = obj.getClass();
        String typeName = classType.getName();
        if (typeName.equals("java.lang.Integer")) {
            return Integer.TYPE;
        } else if (typeName.equals("java.lang.Long")) {
            return Long.TYPE;
        } else if (typeName.equals("java.lang.Float")) {
            return Float.TYPE;
        } else if (typeName.equals("java.lang.Double")) {
            return Double.TYPE;
        } else if (typeName.equals("java.lang.Character")) {
            return Character.TYPE;
        } else if (typeName.equals("java.lang.Boolean")) {
            return Boolean.TYPE;
        } else if (typeName.equals("java.lang.Short")) {
            return Short.TYPE;
        } else if (typeName.equals("java.lang.Byte")) {
            return Byte.TYPE;
        }
        return classType;
    }
}
