package com.pactera.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by pactera on 2017/4/14.
 */
public class RedisPoolUntil {

   private static volatile JedisPool jedisPool = null;
   private RedisPoolUntil(){}
   public static JedisPool getJedisPoolInstance(){
      if (null == jedisPool) {
         synchronized (RedisPoolUntil.class){
            if (null == jedisPool){
               JedisPoolConfig config = new JedisPoolConfig();
               config.setMaxTotal(500);
               config.setMaxIdle(20);
               config.setMaxWaitMillis(60*1000);
               config.setTestOnBorrow(true);
               jedisPool = new JedisPool(config,"192.168.27.129",6379,10*1000,"root");
            }
         }
      }
      return jedisPool;
   }
   public static void release(JedisPool pool, Jedis jedis){
      if (null != jedis) {
         pool.returnResourceObject(jedis);
      }
   }
}
