package com.zhao.hash;

import redis.clients.jedis.Jedis;

public class IDGenerator {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        try {
            long id =jedis.incr("id");
            System.out.println("从redis中获取的分布式id   "+id);
        }finally {
            if (null!=jedis){
                jedis.close();
            }
        }
    }
}
