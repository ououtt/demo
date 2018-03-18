package com.example.demo.service;

import com.example.demo.domain.entity.UserDO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 13:56
 **/
public interface UserService extends UserDetailsService {

    int createComonUser(UserDO userDO);
}
