package com.pactera.service;

import org.apache.shiro.authz.annotation.RequiresRoles;

import java.util.Date;

/**
 * Created by Pactera on 2017/3/29.
 */
public class AnnotationTestService {

    @RequiresRoles({"admin"})
    public void testAnnotation(){
        System.out.println("来啦！ time" +new Date());

    }
}
