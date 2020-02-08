package com.lgx.dao;

import com.lgx.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 用户数据库操作
 *
 */
@Mapper
public interface UserMapper {
    //登录验证
    Map<String,Object> loginPage(User user);
    //查询通过ID单个用户信息
    Map<String,Object> selectUserById(String id);
    //增加用户
    boolean insertUser(User user);
    //修改用户信息
    int updateUser(User user);
    //删除用户信息
    int deleteUserById(String id);
}
