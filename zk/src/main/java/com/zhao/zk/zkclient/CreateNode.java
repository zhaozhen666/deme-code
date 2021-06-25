package com.zhao.zk.zkclient;

import org.I0Itec.zkclient.ZkClient;

public class CreateNode {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("119.45.52.68:2181");
        System.out.println("connect success");
        //true代表可以递归创建目录
        zkClient.createPersistent("/zkclient/persistent/children",true);
        System.out.println("create node success");
    }
}
