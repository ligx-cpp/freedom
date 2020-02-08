package com.lgx.controller;

import com.lgx.entity.User;
import com.lgx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //跳转登录页面
    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }
    //跳转注册页面
    @RequestMapping("/register")
    public String register(){
        return "register";
    }
    //用户登录
    @ResponseBody
    @RequestMapping("/logins")
    public String logins(@RequestParam("username") String username,@RequestParam("password") String password){
        Map<String,Object> user=userService.loginverify(username,password);
        if(user==null){
            return "登陆失败";
        }else{
            System.out.println("登录用户信息："+user.toString());
        }
        return "登陆成功";
    }

    //用户注册
    @ResponseBody
    @RequestMapping("/add")
    public String addUser(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("password2") String password2,
                          @RequestParam("gender") String gender,
                          @RequestParam("email") String email){
        if(!password.equals(password2)){
            return "两次密码不相同，注册失败！！";
        }else {
            User tmp=new User();
            tmp.setUsername(username);
            tmp.setPassword(password);
            tmp.setGender(gender);
            tmp.setEmail(email);
            boolean res = userService.addUserInfoDao(tmp);
            if (!res) {
                return "注册失败！";
            } else {
                return "注册成功！";
            }
        }
    }
}
