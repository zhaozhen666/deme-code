package com.zhao.zk.nativeapi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class UpdateNode implements Watcher {

    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception{
        zooKeeper = new ZooKeeper("119.45.52.68:2181",6000,new UpdateNode());
        Thread.sleep(Integer.MAX_VALUE);

    }
    @Override
    public void process(WatchedEvent event) {
        try {
            byte[] before = zooKeeper.getData("/persistent/children",false,null);
            System.out.println("修改前的值"+new String(before));
           Stat stat = zooKeeper.setData("/persistent","客户端修改的内容".getBytes(),-1);
            System.out.println(stat);
            byte[] after = zooKeeper.getData("/persistent/children",false,null);
            System.out.println("修改后的值"+new String(after));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
