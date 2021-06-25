package com.zhao.zk.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

public class GetNodeData {
    public static void main(String[] args) throws Exception{
        String path = "/zkclient-node";
        ZkClient zkClient = new ZkClient("119.45.52.68:2181");
        boolean exist = zkClient.exists(path);
        if (!exist){
            zkClient.createEphemeral(path,"123");
        }
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println(dataPath+"节点内容被更新"+data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println(dataPath+"节点内容被删除");

            }
        });
        Object o = zkClient.readData(path);
        System.out.println(o);

        zkClient.writeData(path,"4567");
        Thread.sleep(2000);

        zkClient.delete(path);
        Thread.sleep(2000);
    }
}
