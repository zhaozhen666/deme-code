package com.zhao.zk.zkclient;

import org.I0Itec.zkclient.ZkClient;

public class createSession {
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("119.45.52.68:2181");
        System.out.println("connect success");
    }
}
