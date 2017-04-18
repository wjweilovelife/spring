package com.pactera.controller;

import com.pactera.service.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by pactera on 2017/4/14.
 */
@Controller
@RequestMapping("mq")
public class RocketmqController {
   @Autowired
   MqService mqService;

   @RequestMapping("/send")
   public void sendMessage(@RequestParam("message") String message) throws Exception {
      mqService.sendMessage(message);
   }
}
