package com.pactera.service.impl;

import com.pactera.rocketmq.MqProducer;
import com.pactera.service.MqService;
import com.pactera.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by pactera on 2017/4/17.
 */
@Service
public class MqServiceImpl implements MqService{

   @Autowired
   MqProducer mqProducer;

   @Override
   public void sendMessage(String message) throws Exception {
      String result = null;
      String json = "";
      json = JsonUtil.object2Json(message);
      result = mqProducer.sendMessage(json);
      System.out.println("mq send ok :"+result);
   }
}
