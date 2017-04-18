package com.pactera.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

/**
 * Created by pactera on 2017/4/13.
 */
public class RedisTest {
   private static final String REDIS_IP = "192.168.27.129";
   private static final int REDIS_PORT = 6379;

   public static void main(String[] args) {
      Jedis jedis = new Jedis(REDIS_IP, REDIS_PORT);
      jedis.auth("root");
      System.out.println(jedis.ping());
      /**
       * redis 事物
       */
      Transaction transaction = jedis.multi();
      transaction.set("wjwei","1991");
      transaction.set("lyfeng","1993");
//      transaction.exec();//执行
      transaction.discard();//放弃
      /**
       *加锁
       */
//      RedisTest test = new RedisTest();
//      boolean reValue = test.transMethod();
//      System.out.println("......main reValue is "+reValue);

      /**
       * 主从复制
       */
      Jedis m_jedis = new Jedis(REDIS_IP,6379);
      m_jedis.auth("root");
      Jedis s_jedis = new Jedis(REDIS_IP,6380);
      s_jedis.auth("root");
      s_jedis.slaveof(REDIS_IP,REDIS_PORT);//设置主服务
      m_jedis.set("class","RedisTest.java");
      String result = s_jedis.get("class");
      System.out.println(result);

      /**
       * 连接池
       */
      JedisPool jedisPool = RedisPoolUntil.getJedisPoolInstance();
      Jedis jedis1 = null;
      try {
         jedis = jedisPool.getResource();
         jedis.set("hello","world");
      }catch (Exception e){
         e.printStackTrace();
      }finally {
         RedisPoolUntil.release(jedisPool,jedis);
      }
   }
   public boolean transMethod(){
      int realBushLines = 20;
      Jedis jedis = new Jedis(REDIS_IP, REDIS_PORT);
      jedis.auth("root");
      jedis.watch("balance");
      int balance = Integer.parseInt(jedis.get("balance"));
      if (realBushLines > balance) {
         jedis.unwatch();
         System.out.println("余额不足，请及时充值！");
         return false;
      }else{
         System.out.println("........into transaction...");
         Transaction transaction = jedis.multi();
         transaction.decrBy("balance",realBushLines);
         transaction.incrBy("debt",realBushLines);
         transaction.exec();
         balance = Integer.parseInt(jedis.get("balance"));
         int debt = Integer.parseInt(jedis.get("debt"));
         System.out.println("balance:"+balance+".....debt:"+debt);
         return true;
      }
   }
}
