package com.example.demo.service;

import com.example.demo.domain.entity.UserDO;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 13:56
 **/
public interface UserService {

    UserDO loadUserByUsername(String username);

    int createComonUser(UserDO userDO);
}
