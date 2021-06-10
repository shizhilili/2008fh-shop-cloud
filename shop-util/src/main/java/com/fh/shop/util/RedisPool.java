package com.fh.shop.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {

    private  RedisPool(){

    }

    private static JedisPool jedisPool;
    private static void initPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置最大连接数量
        jedisPoolConfig.setMaxTotal(1000);
        //设置闲置连接数量
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMinIdle(100);
        jedisPoolConfig.setTestOnReturn(true);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPool = new JedisPool(jedisPoolConfig, "192.168.17.139", 6380);

    }
    //静态块，里面的代码只执行一次
    static {
        initPool();
    }

    public static Jedis getConnection(){
        return jedisPool.getResource();
    }
}
