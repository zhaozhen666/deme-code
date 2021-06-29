package com.zhao.rpc.config;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class RpcConfigAbstract {

    private AtomicInteger generator = new AtomicInteger(0);

    protected String id;

    protected String interfaceClass = null;

    protected Class<?> proxyClass =null;

    public String getId(){
        if (StringUtils.isBlank(id)){
            id= "rapic-cfg-gen-"+generator.getAndIncrement();
        }
        return id;
    }

    public void setId(String id){
        this.id=id;
    }

    public String getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(String interfaceClass) {
        this.interfaceClass = interfaceClass;
    }
}
