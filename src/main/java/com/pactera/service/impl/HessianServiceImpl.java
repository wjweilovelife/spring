package com.pactera.service.impl;

import com.pactera.domain.Pactera;
import com.pactera.service.HessianService;
import com.pactera.utils.JsonUtil;

/**
 * Created by pactera on 2017/4/18.
 */
public class HessianServiceImpl implements HessianService {

   @Override
   public String getPactera() throws Exception {
      String json = null;
      Pactera pactera = new Pactera();
      pactera.setAddress("北京国贸中国海洋航空工业区");
      pactera.setName("北京中国海洋航空工业公司");
      json = JsonUtil.object2Json(pactera);
      return json;
   }

   @Override
   public String sayHello() {
      return "hello world ";
   }
}
