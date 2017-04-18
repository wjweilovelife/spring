package com.pactera.realm;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Pactera on 2017/3/28.
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        if (StringUtils.isEmpty(username)) {
            return null;
        }else{
//            if ("unknown".equals(username)) {
//                throw new UnknownAccountException("用户不存在");
//            }
//            if ("monster".equals(username)) {
//                throw new LockedAccountException("用户被锁定");
//            }
            Object hashedCredentials = null;
            if ("admin".equals(username)) {
                hashedCredentials = new SimpleHash("MD5", "admin", "admin", 1024);
            } else if ("user".equals(username)) {
                hashedCredentials = new SimpleHash("MD5", "user", "user", 1024);
            }
            return new SimpleAuthenticationInfo(username, hashedCredentials, ByteSource.Util.bytes(username), getName());
        }
    }

    public static void main(String[] args) {
        String algorithmName = "MD5";
        Object source = "123";
        Object salt = "pactera";
        int hashIterations = 1024;
        Object result = new SimpleHash(algorithmName, source, salt, hashIterations);
        System.out.println(result);

        //md5加密，不加盐
        String password_md5 = new Md5Hash("123").toString();
        System.out.println("md5加密，不加盐=" + password_md5);
        //md5加密，加盐，一次散列
        String password_md5_sale_1 = new Md5Hash("123", "pactera", 1).toString();
        System.out.println("password_md5_sale_1=" + password_md5_sale_1);
        String password_md5_sale_2 = new Md5Hash("123", "pactera", 2).toString();
        System.out.println("password_md5_sale_2=" + password_md5_sale_2);
        //两次散列相当于md5(md5())

    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String username = (String) principalCollection.getPrimaryPrincipal();
        if (StringUtils.isEmpty(username)) {
            return null;
        }else{
            Set<String> roles = new HashSet<String>();
            roles.add("user");
            if ("admin".equals(username)) {
                roles.add("admin");
            }
            info.addRoles(roles);
            return info;
        }
    }
}
