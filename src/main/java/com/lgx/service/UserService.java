package com.lgx.service;

import com.lgx.dao.UserMapper;
import com.lgx.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public boolean addUserInfoDao(User user){
        return userMapper.insertUser(user);
    }
    public Map<String, Object> loginverify(String username, String password){
        if (username ==null || password == null){
            System.out.println("用户名或密码为空！");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Map<String,Object> userInfo = userMapper.loginPage(user);
        if (userInfo == null){
            System.out.println("用户名或密码错误");
        }
        return userInfo;
    }
}
