package com.example.demo.service;

import com.example.demo.domain.entity.UserDO;
import com.example.demo.web.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 13:56
 **/
public interface UserService extends UserDetailsService {

    int createCommonUser(UserDO userDO);

    int countByUsername(String username);

    List<UserDTO> selectAll();
}
