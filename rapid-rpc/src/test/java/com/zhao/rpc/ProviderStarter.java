package com.zhao.rpc;

import java.util.ArrayList;
import java.util.List;

import com.zhao.rpc.provider.ProviderConfig;
import com.zhao.rpc.provider.RpcServerConfig;

public class ProviderStarter {

    public static void main(String[] args) {

        //	服务端启动
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 每一个具体的服务提供者的配置类
                    ProviderConfig providerConfig = new ProviderConfig();
                    providerConfig.setInterfaceClass("com.zhao.rpc.HelloService");
                    HelloServiceImpl hellpHelloServiceImpl = HelloServiceImpl.class.newInstance();
                    providerConfig.setRef(hellpHelloServiceImpl);

                    //	把所有的ProviderConfig 添加到集合中
                    List<ProviderConfig> providerConfigs = new ArrayList<ProviderConfig>();
                    providerConfigs.add(providerConfig);

                    RpcServerConfig rpcServerConfig = new RpcServerConfig(providerConfigs);
                    rpcServerConfig.setPort(8765);
                    rpcServerConfig.exporter();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
