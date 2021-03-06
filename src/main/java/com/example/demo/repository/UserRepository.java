package com.example.demo.repository;

import com.example.demo.domain.entity.UserDO;
import com.example.demo.repository.orm.User;

import java.util.List;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 14:00
 **/
public interface UserRepository {

    int createUser(UserDO userDO);

    UserDO findUserDOByUsername(String username);

    int countUserByUsername(String username);

    List<User> selectAll();

}
