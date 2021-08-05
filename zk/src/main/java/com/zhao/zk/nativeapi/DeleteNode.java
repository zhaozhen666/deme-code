package com.zhao.zk.nativeapi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class DeleteNode implements Watcher {
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper("119.45.52.68:2181", 6000, new UpdateNode());
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
        try {
            Stat exists = zooKeeper.exists("/persistent/children", false);
            System.out.println(exists == null ? "该节点不存在" : "该节点存在");
            zooKeeper.delete("/persistent/children", -1);
            Stat exists2 = zooKeeper.exists("/persistent/children", false);
            System.out.println(exists2 == null ? "该节点不存在" : "该节点存在");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
