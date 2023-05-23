package com.example.springboot.service;

import com.example.springboot.pojo.User;

/**
 * @author 小红
 * @create 2023/5/22 10:53
 */

public interface UserService {
    public User queryUserByName(String name);
}
