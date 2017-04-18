package com.pactera.filter;

import java.util.LinkedHashMap;

/**
 * Created by Pactera on 2017/3/29.
 */
public class FilterChainDefinitionMapBuilder {

    /**
     * 此处从数据库读取权限信息
     * @return
     */
    public LinkedHashMap<String,String> builderFilterChainDefinitionMap(){
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("/view/login.jsp","anon");
        map.put("/view/mq.jsp","anon");//只是测试mq，没其他作用
        map.put("/hessian/*","anon"); //测试hessian使用
        map.put("/shiro/login","anon");
        map.put("/logout","logout");
        map.put("/view/user.jsp","authc,roles[user]");
        map.put("/view/admin.jsp","authc,roles[admin]");
//        map.put("/view/list.jsp","user");
        map.put("/**","authc");
        return map;
    }
}
