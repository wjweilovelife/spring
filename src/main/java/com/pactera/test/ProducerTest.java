package com.pactera.test;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * Created by pactera on 2017/4/17.
 */
public class ProducerTest {
   public static void main(String[] args) throws MQClientException,
         InterruptedException {
      /**
       * ProducerGroupName保证唯一
       */
      final DefaultMQProducer producer = new DefaultMQProducer(
            "ProducerGroupName");
      //nameserver服务,多个以;分开
      producer.setNamesrvAddr("192.168.27.129:9876");
      producer.setInstanceName("Producer");
//      producer.setCreateTopicKey("TopicTest1");
//      producer.setVipChannelEnabled(false);

      /**
       * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
       * 注意：切记不可以在每次发送消息时，都调用start方法
       */
      producer.start();

      /**
       * 下面这段代码表明一个Producer对象可以发送多个topic，多个tag的消息。
       * 注意：send方法是同步调用，只要不抛异常就标识成功。但是发送成功也可会有多种状态，<br>
       * 例如消息写入Master成功，但是Slave不成功，这种情况消息属于成功，但是对于个别应用如果对消息可靠性要求极高，<br>
       * 需要对这种情况做处理。另外，消息可能会存在发送失败的情况，失败重试由应用来处理。
       */
      for (int i = 0; i < 10; i++) {
         try {
            {      //通过topic订阅消息，tag过滤消息
               Message msg = new Message("TopicTest1",// topic
                     "TagA",// tag 消息标签，只支持设置一个Tag（服务端消息过滤使用）
                     "OrderID001",// key 消息关键词，多个Key用KEY_SEPARATOR隔开（查询消息使用）
                     ("Hello MetaQA").getBytes());// body
               SendResult sendResult = producer.send(msg);
               System.out.println(sendResult);
            }

            {
               Message msg = new Message("TopicTest2",// topic
                     "TagB",// tag
                     "OrderID0034",// key
                     ("Hello MetaQB").getBytes());// body
               SendResult sendResult = producer.send(msg);
               System.out.println(sendResult);
            }

            {
               Message msg = new Message("TopicTest3",// topic
                     "TagC",// tag
                     "OrderID061",// key
                     ("Hello MetaQC").getBytes());// body
               SendResult sendResult = producer.send(msg);
               System.out.println(sendResult);
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
         TimeUnit.MILLISECONDS.sleep(1000);
      }

      /**
       * 应用退出时，要调用shutdown来清理资源，关闭网络连接，从MetaQ服务器上注销自己
       * 注意：我们建议应用在JBOSS、Tomcat等容器的退出钩子里调用shutdown方法
       */
      Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
         public void run() {
            producer.shutdown();
         }
      }));
      System.exit(0);
   }
}
