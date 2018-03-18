package com.example.demo.repository;

import com.example.demo.domain.entity.UserDO;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 14:00
 **/
public interface UserRepository {

    int createUser(UserDO userDO);

    UserDO findUserDOByUsername(String username);

}
