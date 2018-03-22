package com.example.demo.service.impl;

import com.example.demo.constant.Constant;
import com.example.demo.constant.enums.UserRoleEnum;
import com.example.demo.domain.entity.UserDO;
import com.example.demo.domain.value.RoleValue;
import com.example.demo.factory.RoleFactory;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 13:56
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleFactory roleFactory;

    private static List<RoleValue> COMMON_USER_ROLE;

    static {
        RoleValue roleValue = new RoleValue();
        UserRoleEnum comon = UserRoleEnum.COMMON_USER;
        roleValue.setId(comon.getId());
        roleValue.setRoleName(comon.getRoleName());
        roleValue.setState(Constant.VALID);
        COMMON_USER_ROLE = Collections.singletonList(roleValue);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO userDO = userRepository.findUserDOByUsername(username);
        if (userDO == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return userDO;
    }

    @Override
    public int createCommonUser(UserDO userDO) {
        userDO.setRoles(COMMON_USER_ROLE);
        userDO.setPassword(passwordEncoder.encode(userDO.getPassword()));
        return userRepository.createUser(userDO);
    }

    @Override
    public int countByUsername(String username) {
        return userRepository.countUserByUsername(username);
    }


}
