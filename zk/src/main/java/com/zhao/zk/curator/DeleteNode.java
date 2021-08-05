package com.zhao.zk.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class DeleteNode {
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("119.45.52.68:2181")
                .sessionTimeoutMs(5000)
                .sessionTimeoutMs(3000)
                .retryPolicy(retryPolicy)
                //.namespace("base")
                .build();
        client.start();
        String path = "/curator";
        client.delete().deletingChildrenIfNeeded().withVersion(-1).forPath(path);
        System.out.println("delete node success ");
    }
}
