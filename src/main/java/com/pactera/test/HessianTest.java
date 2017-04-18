package com.pactera.test;

import com.caucho.hessian.client.HessianProxyFactory;
import com.pactera.domain.Pactera;
import com.pactera.service.HessianService;
import com.pactera.utils.JsonUtil;

/**
 * Created by pactera on 2017/4/18.
 */
public class HessianTest {
   public static void main(String[] args) throws Exception {
      String url = "http://127.0.0.1:8080/hessian/helloHessian";
      HessianProxyFactory factory = new HessianProxyFactory();
      factory.setOverloadEnabled(true);
      HessianService hessianService = (HessianService) factory.create(HessianService.class, url);
      String json = hessianService.getPactera();
      Pactera pactera = (Pactera) JsonUtil.json2Object(json,Pactera.class);
      System.out.println(pactera.getAddress()+" "+pactera.getName());
   }
}
