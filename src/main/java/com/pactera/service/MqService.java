package com.pactera.service;

/**
 * Created by pactera on 2017/4/17.
 */
public interface MqService {

   void sendMessage(String message) throws Exception;
}
