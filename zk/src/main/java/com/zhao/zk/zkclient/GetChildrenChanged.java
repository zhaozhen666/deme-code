package com.zhao.zk.zkclient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

public class GetChildrenChanged {
    public static void main(String[] args) throws Exception{
        ZkClient zkClient = new ZkClient("119.45.52.68:2181");
        zkClient.createPersistent("/zkClient");
        Thread.sleep(1000);
        List<String> children = zkClient.getChildren("/zkClient");
        System.out.println(children);
        zkClient.subscribeChildChanges("/zkClient", new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println("父路径"+parentPath+"当前子路径的"+currentChilds);
            }
        });
        zkClient.createPersistent("/zkClient/child1");
        Thread.sleep(1000);
        zkClient.delete("/zkClient/child1");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
