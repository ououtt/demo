package com.example.demo.factory.impl;

import com.example.demo.constant.StateConstant;
import com.example.demo.constant.enums.UserRoleEnum;
import com.example.demo.domain.entity.UserDO;
import com.example.demo.domain.value.RoleValue;
import com.example.demo.factory.RoleFactory;
import com.example.demo.repository.orm.Role;
import com.example.demo.repository.orm.UserRoleRelation;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 22:58
 **/
@Component
public class RoleFactoryImpl implements RoleFactory {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<UserRoleRelation> convertUserRoleRelation(UserDO userDO) {
        if (userDO == null) {
            logger.error("convertUserRoleRelation 入参为空");
            return null;
        }
        return convertUserRoleRelation(userDO.getId(), userDO.getRoles());
    }

    @Override
    public List<UserRoleRelation> convertUserRoleRelation(Integer userId, List<RoleValue> roles) {
        if (userId == null || CollectionUtils.isEmpty(roles)) {
            logger.error("convertUserRoleRelation 入参为空");
            return null;
        }
        List<UserRoleRelation> userRoleRelations = new ArrayList<>(roles.size());
        for (RoleValue roleValue : roles) {
            userRoleRelations.add(wrapUserRoleRelation(userId, roleValue));
        }
        return userRoleRelations;
    }

    @Override
    public List<RoleValue> convertRoleListByIds(List<Integer> roleIds) {
        return roleIds.stream().filter(Objects::nonNull).map(this::wrapRoleValue).collect(Collectors.toList());
    }

    @Override
    public List<RoleValue> convertRoleList(List<Role> roles) {
        List<RoleValue> roleValues = new ArrayList<>(roles.size());
        for (Role role : roles) {
            RoleValue roleValue = new RoleValue();
            BeanUtils.copyProperties(role, roleValue);
            roleValues.add(roleValue);
        }
        return roleValues;
    }

    private RoleValue wrapRoleValue(Integer roleId) {
        UserRoleEnum userRoleEnum = UserRoleEnum.parseId(roleId);
        RoleValue roleValue = new RoleValue();
        roleValue.setId(roleId);
        roleValue.setRoleName(userRoleEnum.getRoleName());
        roleValue.setState(StateConstant.VALID);
        return roleValue;
    }

    private UserRoleRelation wrapUserRoleRelation(Integer userId, RoleValue roleValue) {
        UserRoleRelation userRoleRelation = new UserRoleRelation();
        userRoleRelation.setUserId(userId);
        userRoleRelation.setRoleId(roleValue.getId());
        return userRoleRelation;
    }
}
