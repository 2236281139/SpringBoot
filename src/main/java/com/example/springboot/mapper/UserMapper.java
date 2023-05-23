package com.example.springboot.mapper;

import com.example.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 小红
 * @create 2023/5/22 10:51
 */
@RequestMapping
@Mapper
//这个注解表示了这是一个mybatis的mapper类
public interface UserMapper {

    User queryUserByName(String name);
}
