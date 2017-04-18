package com.pactera.controller;

import com.pactera.exception.MyException;
import com.pactera.service.AnnotationTestService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.omg.IOP.ExceptionDetailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Pactera on 2017/3/28.
 */
@Controller
@RequestMapping("shiro")
public class ShiroHandlerController {

    private static final Logger log = LoggerFactory.getLogger(ShiroHandlerController.class);

    @Autowired
    AnnotationTestService annotationTestService;

    @RequestMapping("/testAnnotation")
    public String testMethod() {
        try {
            annotationTestService.testAnnotation();
        } catch (Exception e) {
            log.error("$$$$" + e.getMessage(), e);
//            throw new MyException(e.getMessage());
        }
        return "redirect:/view/list.jsp";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        @RequestParam(value = "remember",required = true,defaultValue = "off")  String remember) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            if ("on".equals(remember)) {
                token.setRememberMe(true);
            }
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                System.out.println("登录失败:" + uae.getMessage());
                throw new UnknownAccountException("$$$$UnknownAccountException");
            } catch (IncorrectCredentialsException ice) {
                System.out.println("登录失败:" + ice.getMessage());
                throw new IncorrectCredentialsException("$$$$IncorrectCredentialsException");
            } catch (LockedAccountException lae) {
                throw new LockedAccountException("$$$$LockedAccountException");
            } catch (Exception e) {
                throw new MyException(e.getMessage());
            }
            currentUser.getSession().setAttribute("username","admin");
        }
        return "redirect:/view/list.jsp";
    }
}
