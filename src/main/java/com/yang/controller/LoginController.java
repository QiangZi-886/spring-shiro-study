package com.yang.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {


    //这里就不再追加前端的thymeleaf页面了，这里只有纯后端代码

    @PostMapping("login")
    public String login(String username,String password){
        //要和shiro框架结合起来，使用的是Subject的login方法进行调用的，login方法会调用到自定义的Realm类中中的认证方法
        Subject subject = SecurityUtils.getSubject();
        //构建UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            //账户错误
            e.printStackTrace();
            throw new RuntimeException(e);
        }catch (IncorrectCredentialsException e){
            //密码错误
            e.printStackTrace();
            throw new IncorrectCredentialsException(e);
        }
        return "角色、权限认证通过之后需要跳转的资源";


    }




}
