package com.pactera.rocketmq;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.pactera.utils.PubUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pactera on 2017/4/17.
 */
@Service
public class MqConsumer implements MessageListenerConcurrently {
   @Override
   public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
      MessageExt msg = list.get(0);
      String messageJson = new String(msg.getBody());
      String topicName = msg.getTopic();
      if (!PubUtils.isNull(topicName) && !PubUtils.isNull(messageJson)) {
         if ("topic_test".equals(topicName)){
            System.out.println(messageJson);
         }
      }else{
         return ConsumeConcurrentlyStatus.RECONSUME_LATER;
      }
      return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
   }
}
