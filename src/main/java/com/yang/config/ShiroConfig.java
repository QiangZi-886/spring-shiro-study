package com.yang.config;


import com.yang.realm.AccountRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //在这里进行权限的细粒度控制
        Map<String,String> map = new HashMap<>();
        //这里的意思是访问"main"这个资源的时候，必须是处于登录状态
        map.put("/main","authc");
        //访问manage资源的时候必须是manage权限
        map.put("/manage","perms[manage]");
        //访问administator资源时必须拥有administator角色
        map.put("/administrator","roles[administrator]");

        //在这里也可以额外指定登录url、未授权页面等等
        shiroFilterFactoryBean.setLoginUrl("/登录请求");
        shiroFilterFactoryBean.setUnauthorizedUrl("/未授权页面提示");



        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }


    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("accountRealm") AccountRealm accountRealm){
        //上面的注解时指定获取bean的方法，在执行到此方法时，会先去创建AccountRealm的Bean，然后在注入到此方法参数中来
        //需要将自定义的realm注入到此Manager中进行管理
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(accountRealm);
        return defaultWebSecurityManager;
    }


    @Bean
    public AccountRealm accountRealm(){
        return new AccountRealm();
    }

}
