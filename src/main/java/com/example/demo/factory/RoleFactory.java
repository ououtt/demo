package com.example.demo.factory;

import com.example.demo.domain.entity.UserDO;
import com.example.demo.domain.value.RoleValue;
import com.example.demo.repository.orm.Role;
import com.example.demo.repository.orm.UserRoleRelation;

import java.util.List;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 22:57
 **/
public interface RoleFactory {

    List<UserRoleRelation> convertUserRoleRelation(UserDO userDO);

    List<UserRoleRelation> convertUserRoleRelation(Integer userId, List<RoleValue> roles);

    List<RoleValue> convertRoleListByIds(List<Integer> roleIds);

    List<RoleValue> convertRoleList(List<Role> roles);
}
