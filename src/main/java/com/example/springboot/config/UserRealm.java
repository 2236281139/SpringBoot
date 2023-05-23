package com.example.springboot.config;

import com.example.springboot.pojo.User;
import com.example.springboot.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 小红
 * @create 2023/5/23 9:43
 */
public class UserRealm extends AuthorizingRealm {

    //授权
    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权=》doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:add");

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("执行了认证=》doGetAuthenticationInfo");
        //连接真实数据库
        //用户名，密码~自己设置的
//        String name="gql";
//        String password="123456";
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        User user = userService.queryUserByName(userToken.getUsername());
        if (user == null) {
            return null;//自己会报异常，不用我们自己定义
        }

        //抛出异常，这里的异常就是我们之前写的,用户名不存在
        //第一个参数principal:获取当前用户认证
        //第二个参数credentials：要传入密码的对象
        //第二个参数realmName:认证名
        //密码认证，shiro做
        return new SimpleAuthenticationInfo("", user.getPwd(), "");
    }

}


