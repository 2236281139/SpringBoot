package com.example.springboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 小红
 * @create 2023/5/23 9:43
 */
@Controller
public class MyController {
    @RequestMapping({"/", "/index"})
    public String toLogin(Model model) {
        model.addAttribute("msg", "hello shiro");
        return "index";

    }

    @RequestMapping("user/add")
    public String add() {
        return "user/add";
    }

    @RequestMapping("user/update")
    public String update() {
        return "user/update";
    }

    @RequestMapping("tologin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username,String password,Model model){
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        //封装成Token令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            //验证登录，如果没有异常就说明成功了
            subject.login(token);
            return "index";
        }catch (UnknownAccountException e){//用户名不存在
            model.addAttribute("msg", "用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }

    @RequestMapping("/noauth")
    @ResponseBody
    public String unauthorized() {
        return "未授权页面，静止访问！！";
    }
}

