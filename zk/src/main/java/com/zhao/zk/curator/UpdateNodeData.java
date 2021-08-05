package com.zhao.zk.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class UpdateNodeData {
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
        String path = "/curator/child1";
//        client.create().creatingParentContainersIfNeeded()
//                .withMode(CreateMode.PERSISTENT)
//                .forPath(path,"init".getBytes());
//        System.out.println("success create node");
        Stat stat = new Stat();
        byte[] data = client.getData().storingStatIn(stat).forPath(path);
        System.out.println("数据为" + new String(data));

        int version = client.setData().withVersion(stat.getVersion()).forPath(path).getVersion();
        System.out.println("update node " + path + " version " + version);
        client.setData().withVersion(stat.getVersion()).forPath(path).getAversion();
    }
}
