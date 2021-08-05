package com.zhao.zk.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CreateSession {
    public static void main(String[] args) {
        //curator第一种创建回话方式
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("119.45.52.68:2181",
                5000, 3000, retryPolicy);
        curatorFramework.start();
        System.out.println("create session success");
        //第二种方式
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("119.45.52.68:2181")
                .sessionTimeoutMs(5000)
                .sessionTimeoutMs(3000)
                .retryPolicy(retryPolicy)
                .namespace("base")
                .build();
        client.start();
        System.out.println("createe session2 success ");
    }
}
