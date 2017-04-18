package com.pactera.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;

/**
 *
 * @author wjwei
 * @date 2016年2月26日下午5:12:42
 */
@Component
public class MyDefaultMQPushConsumer extends DefaultMQPushConsumer implements InitializingBean {

    @Autowired
    MqConsumer mqConsumer;

//    @Value("${mq.namesrvAddr}")
//    private String namesrvAddr;
//    @Value("${mq.instanceName}")
//    private String instanceName;
//    @Value("${mq.consumerGroup}")
//    private String consumerGroup;
//    @Value("${mq.consumeTopicNames}")
//    private String consumeTopicName;
//    @Value("${mq.consumeThreadMax}")
//    private String consumeThreadMax;
//    @Value("${mq.consumeThreadMin}")
//    private String consumeThreadMin;


    @Override
    public void afterPropertiesSet() throws Exception {
        setNamesrvAddr(PropertyPlaceholder.getProperty("mq.namesrvAddr"));
        setInstanceName(PropertyPlaceholder.getProperty("mq.instanceName"));
        setConsumerGroup(PropertyPlaceholder.getProperty("mq.consumerGroup"));
        String [] strArray = PropertyPlaceholder.getProperty("mq.consumeTopicNames").split(",");
        for(int i = 0;i<strArray.length;i++){
            subscribe(strArray[i],"*");
        }

        registerMessageListener(mqConsumer);

        setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        setConsumeThreadMax(Integer.parseInt(PropertyPlaceholder.getProperty("mq.consumeThreadMax")));
        setConsumeThreadMin(Integer.parseInt(PropertyPlaceholder.getProperty("mq.consumeThreadMin")));
        start();
    }
}
