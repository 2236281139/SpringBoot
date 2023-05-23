package com.example.springboot.service;

/**
 * @author 小红
 * @create 2023/5/22 10:56
 */

import com.example.springboot.mapper.UserMapper;
import com.example.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
