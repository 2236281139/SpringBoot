package com.example.springboot.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 小红
 * @create 2023/5/23 9:42
 */
@Configuration
public class ShiroConfig {
    //shiroFilterFactoryBean

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean( @Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro的内置过滤器
        /*
         * anon:无需认证就可以访问
         * authc:必须认证才能访问
         * user:必须拥有 记住我 功能才能用
         * perms:拥有对某个资源的访问权限才能访问
         * role:拥有某个角色权限才能访问.
         * */
        //拦截
        Map<String,String> filterMap=new LinkedHashMap<>();
        // filterMap.put("/user/add","anon");
        // anon:无需认证就可以访问
        filterMap.put("/user/update","authc");
        //authc:必须认证才能访问
        //授权
        filterMap.put("/user/add","perms[user:add]");
        //设置登录的请求
        bean.setLoginUrl("/tologin");

        // 添加未授权的请求到(ShiroConfig)
        bean.setUnauthorizedUrl("/noauth");

        //被拦截以后跳转到首页
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }

    //DefaultWebSecurityManger
    @Bean(name="securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    //创建realm对象。需要自己定义
    @Bean
    public UserRealm userRealm(){

        return new UserRealm();
    }

    @Bean
    public ShiroDialect getShiroDialect(){
        return  new ShiroDialect();
    }

}
