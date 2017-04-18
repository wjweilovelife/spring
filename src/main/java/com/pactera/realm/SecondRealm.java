package com.pactera.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;


/**
 * Created by Pactera on 2017/3/28.
 */
public class SecondRealm extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        System.out.println("[SecondRealm] doGetAuthenticationInfo");
        if("unknown".equals(username)){
            throw new UnknownAccountException("用户不存在");
        }
        if("monster".equals(username)){
            throw new LockedAccountException("用户被锁定");
        }
        Object hashedCredentials = null;
        if ("admin".equals(username)) {
            hashedCredentials = new SimpleHash("SHA1","admin","admin",1024);
        } else if ("user".equals(username)) {
            hashedCredentials = new SimpleHash("SHA1","user","user",1024);
        }
        SimpleAuthenticationInfo info = null;
        info = new SimpleAuthenticationInfo(username,hashedCredentials, ByteSource.Util.bytes(username),getName());
        return info;
    }

    public static void main(String[] args) {
        String algorithmName = "SHA1";
        Object source = "123";
        Object salt = "pactera";
        int hashIterations = 1024;
        Object result = new SimpleHash(algorithmName, source, salt, hashIterations);
        System.out.println(result);
    }
}
