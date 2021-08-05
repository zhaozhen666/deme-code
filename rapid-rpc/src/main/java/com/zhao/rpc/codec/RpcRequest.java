package com.zhao.rpc.codec;

import lombok.Data;

import java.io.Serializable;

@Data
public class RpcRequest implements Serializable {

    private String requestId;

    private String className;

    private String methodName;

    private Class<?>[] paramterTypes;

    private Object[] paramters;
}
