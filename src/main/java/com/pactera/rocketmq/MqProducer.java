package com.pactera.rocketmq;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.pactera.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by pactera on 2017/4/17.
 */
@Configuration
@Service
public class MqProducer {
   private static final Logger logger = LoggerFactory.getLogger(MqProducer.class);

//   @Value("${mq.namesrvAddr}")
//   private String namesrvAddr;
//   @Value("${mq.instanceName}")
//   private String instanceName;
//   @Value("${mq.producerGroup}")
//   private String producerGroup;
//   @Value("${mq.testTopicName}")
//   private String testTopicName;

   public String sendMessage(String json) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
      Message msg = null;
      SendResult sendResult=null;
      msg = new Message(PropertyPlaceholder.getProperty("mq.testTopicName"),null,"001",(json).getBytes());
      try {
         sendResult = defaultMQProducer().send(msg);
         logger.info("producer send success result :"+sendResult.toString());
      }catch (Exception e){
         logger.error("producer send failure reason :"+e.getMessage());
         throw new BusinessException("producer send failure reason :"+e.getMessage());
      }
      return sendResult.toString();
   }

   @Bean(initMethod = "start",destroyMethod = "shutdown")
   public DefaultMQProducer defaultMQProducer() {
      DefaultMQProducer producer = new DefaultMQProducer(PropertyPlaceholder.getProperty("mq.producerGroup"));
      producer.setNamesrvAddr(PropertyPlaceholder.getProperty("mq.namesrvAddr"));
      producer.setInstanceName(PropertyPlaceholder.getProperty("mq.instanceName"));
      return producer;
   }
}
