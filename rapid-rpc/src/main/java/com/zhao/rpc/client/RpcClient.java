package com.zhao.rpc.client;

public class RpcClient {

    private String serverAddress;
    private  long timeout;

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
}
