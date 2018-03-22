package com.example.demo.repository.impl;

import com.example.demo.constant.Constant;
import com.example.demo.domain.entity.UserDO;
import com.example.demo.factory.RoleFactory;
import com.example.demo.factory.UserFactory;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.mapper.RoleMapper;
import com.example.demo.repository.mapper.UserMapper;
import com.example.demo.repository.mapper.UserRoleRelationMapper;
import com.example.demo.repository.orm.Role;
import com.example.demo.repository.orm.User;
import com.example.demo.repository.orm.UserRoleRelation;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 14:00
 **/
@Repository
public class UserRepositoryImpl implements UserRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;

    @Autowired
    private RoleFactory roleFactory;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int createUser(UserDO userDO) {
        User user = userFactory.convertToOrm(userDO);

        wrapCreate(user);

        int effectCount = userMapper.insertSelective(user);
        if (effectCount != 1) {
            logger.error("创建用户插入表失败");
            return -1;
        }
        int userId = user.getId();
        List<UserRoleRelation> userRoleRelations = roleFactory.convertUserRoleRelation(userId, userDO.getRoles());
        if (CollectionUtils.isNotEmpty(userRoleRelations)) {
            userRoleRelationMapper.insertList(userRoleRelations);
        }
        return userId;
    }

    @Override
    public UserDO findUserDOByUsername(String username) {
        Condition userCondition = new Condition(User.class);
        Example.Criteria criteria = userCondition.createCriteria();
        criteria.andEqualTo("state", Constant.VALID);
        criteria.andEqualTo("username", username);
        List<User> users = userMapper.selectByCondition(userCondition);
        User user = users.get(0);

        Condition condition1 = new Condition(UserRoleRelation.class);
        Example.Criteria criteria1 = condition1.createCriteria();
        criteria1.andEqualTo("userId", user.getId());
        List<UserRoleRelation> userRoleRelations = userRoleRelationMapper.selectByCondition(condition1);

        List<Integer> roleIds = userRoleRelations.stream().map(UserRoleRelation::getRoleId).collect(Collectors.toList());
        Condition condition2 = new Condition(Role.class);
        Example.Criteria criteria2 = condition2.createCriteria();
        criteria2.andEqualTo("state", Constant.VALID);
        criteria2.andIn("id", roleIds);
        List<Role> roles = roleMapper.selectByCondition(condition2);

        UserDO userDO = userFactory.convertToDO(user);
        userDO.setRoles(roleFactory.convertRoleList(roles));
        return userDO;
    }

    private void wrapCreate(User user) {
        Instant now = Instant.now();
        user.setGmtUpdate(now);
        user.setGmtCreate(now);
        user.setState(Constant.VALID);
        user.setValid(Constant.VALID);
    }
}
