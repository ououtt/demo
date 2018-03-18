package com.example.demo.factory.impl;

import com.example.demo.domain.entity.UserDO;
import com.example.demo.factory.UserFactory;
import com.example.demo.repository.orm.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 19:18
 **/
@Component
public class UserFactoryImpl implements UserFactory {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User convertToOrm(UserDO userDO) {
        if (userDO == null) {
            logger.error("UserFactory convertToOrm 入参为空");
            return null;
        }
        User user = new User();
        user.setUsername(userDO.getUsername());
        user.setPassword(userDO.getPassword());
        user.setState(userDO.getState());
        user.setAddress(userDO.getAddress());
        user.setEmail(userDO.getEmail());
        user.setCityCode(userDO.getCityCode());
        user.setGmtCreate(userDO.getCreate());
        user.setId(userDO.getId());
        user.setIdCard(userDO.getIdCard());
        user.setImages(userDO.getImages());
        user.setSex(userDO.getSex());
        user.setMobilePhone(userDO.getMobilePhone());
        user.setValid(userDO.getValid());
        return user;
    }

    @Override
    public UserDO convertToDO(User user) {
        UserDO userDO = new UserDO();
        userDO.setPassword(user.getPassword());
        userDO.setUsername(user.getUsername());
        userDO.setSex(user.getSex());
        userDO.setMobilePhone(user.getMobilePhone());
        userDO.setImages(user.getImages());
        userDO.setIdCard(user.getIdCard());
        userDO.setId(user.getId());
        userDO.setEmail(user.getEmail());
        userDO.setCityCode(user.getCityCode());
        userDO.setAddress(user.getAddress());
        userDO.setCreate(user.getGmtCreate());
        userDO.setState(user.getState());
        userDO.setValid(user.getValid());
        return userDO;
    }
}
