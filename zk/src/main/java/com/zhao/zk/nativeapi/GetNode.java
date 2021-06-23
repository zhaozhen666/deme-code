package com.zhao.zk.nativeapi;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class GetNode implements Watcher {

    private  static ZooKeeper zooKeeper;

    public static void main(String[] args) throws InterruptedException, IOException {
        zooKeeper = new ZooKeeper("119.45.52.68:2181",10000,new GetNode());
        //countDownLatch.await();
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
//        if(event.getType() ==Event.EventType.NodeChildrenChanged){
////再次获取节点数据
//            try {
//                List<String> children =
//                        zooKeeper.getChildren(event.getPath(), true);
//                System.out.println(children);
//            } catch (KeeperException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        //当连接创建了，服务端发送给客户端SyncConnected事件
        if(event.getState() == Event.KeeperState.SyncConnected){
            try {
//调⽤获取单个节点数据⽅法
                getNodeData();
                getChildren();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    private static void getNodeData() throws Exception {
//        byte[] data = zooKeeper.getData("/persistent/children", true,
//                null);
//        System.out.println(new String(data,"utf-8"));

        byte[] data = zooKeeper.getData("/persistent", true,
                null);
        System.out.println(new String(data,"utf-8"));
    }

    public static  void getChildren() throws Exception{
        List<String> childrens = zooKeeper.getChildren("/persistent",true);
        System.out.println(childrens);

    }
}
