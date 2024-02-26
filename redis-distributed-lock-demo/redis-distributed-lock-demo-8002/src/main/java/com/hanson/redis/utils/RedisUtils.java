package com.hanson.redis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {

    private static JedisPool jedisPool;
    static{
        //创建连接池 JedisPoolConfig  基本配置信息
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(5);
        poolConfig.setMaxIdle(1);
        // ....其他配置
        String host = "127.0.0.1";
        int port = 6379;
        jedisPool= new JedisPool(poolConfig,host,port);
    }

    public static Jedis getJedis(){
        Jedis jedis = jedisPool.getResource();
//        jedis.auth("");
        return jedis;
    }
    //close
    public static void close(Jedis jedis){
        jedis.close();
    }
}
