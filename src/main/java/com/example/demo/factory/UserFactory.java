package com.example.demo.factory;

import com.example.demo.domain.entity.UserDO;
import com.example.demo.repository.orm.User;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 19:18
 **/
public interface UserFactory {

    User convertToOrm(UserDO userDO);

    UserDO convertToDO(User user);
}
