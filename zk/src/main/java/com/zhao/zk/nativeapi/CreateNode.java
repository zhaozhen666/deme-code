package com.zhao.zk.nativeapi;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

public class CreateNode implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper("119.45.52.68:2181", 6000, new CreateNode());
        //countDownLatch.await();
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
//            countDownLatch.countDown();
            System.out.println("connect success");
        }
        try {
            createNodeSync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createNodeSync() throws Exception {
        String persistentNode = zooKeeper.create("/persistent/children", "持久节点的子节点内容".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

//        String persistentNode = zooKeeper.create("/persistent","持久节点的内容".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        String psequenceNode = zooKeeper.create("/psequence","持久顺序节点的内容".getBytes("utf-8"),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT_SEQUENTIAL);
//        String linshi = zooKeeper.create("/linshi","临时节点的内容".getBytes("utf-8"),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
//        System.out.println("创建的持久节点是"+persistentNode);
//        System.out.println("创建的持久顺序节点是"+psequenceNode);
//        System.out.println("创建的临时节点是"+linshi);
    }
}
