package com.pactera.filter;

import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by Pactera on 2017/3/30.
 */
@Component
public class MyShiroFilter extends AuthorizationFilter {
    private static final long EXPIRED_TIME = 180000;
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        System.out.println("进来了。。。");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(true);
        Object obj = session.getAttribute("username");
        long startTime = session.getLastAccessedTime();
        long nowTime = new Date().getTime();
        String uri = request.getRequestURI();
        if ("/".equals(uri)) {
            response.sendRedirect("/view/login.jsp");
        }
        else if (nowTime - startTime > EXPIRED_TIME) {
            response.sendRedirect("/view/login.jsp");
        }
        else if (null == obj || ((String)obj).length() == 0) {
            response.sendRedirect("/view/login.jsp");
        }
        return true;
    }
}
