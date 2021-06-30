package com.zhao.rpc.provider;

import com.zhao.rpc.server.RpcServer;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class RpcServerConfig {

    private static  final  String host ="127.0.0.1";

    private Integer port;

    private RpcServer rpcServer;

    private List<ProviderConfig> providerConfigs;


    public RpcServerConfig(List<ProviderConfig> providerConfigs) {
        this.providerConfigs=providerConfigs;
    }

    public void  exporter(){
        if (rpcServer!=null){
            try{
                 rpcServer = new RpcServer(host+":"+port);
            }catch (Exception e ){
                log.error("rpcserver config exception error");
            }
            for (ProviderConfig providerConfig:providerConfigs){
                rpcServer.registerProcessor(providerConfig);
            }
        }

    }
}
