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
import java.time.LocalDateTime;
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
        if (CollectionUtils.isEmpty(users)) {
            logger.error("用户名不存在,username:{}", username);
            return null;
        }
        if (users.size() > 1) {
            logger.error("存在多名相同用户名的用户,username:{}", username);
        }
        User user = users.get(0);
        UserDO userDO = userFactory.convertToDO(user);

        Condition userRoleCondition = new Condition(UserRoleRelation.class);
        Example.Criteria criteria1 = userRoleCondition.createCriteria();
        criteria1.andEqualTo("userId", user.getId());
        List<UserRoleRelation> userRoleRelations = userRoleRelationMapper.selectByCondition(userRoleCondition);
        if (CollectionUtils.isEmpty(userRoleRelations)) {
            logger.error("该用户没有关联角色username:{}", username);
            return userDO;
        }

        List<Integer> roleIds = userRoleRelations.stream().map(UserRoleRelation::getRoleId).collect(Collectors.toList());
        Condition roleCondition = new Condition(Role.class);
        Example.Criteria criteria2 = roleCondition.createCriteria();
        criteria2.andEqualTo("state", Constant.VALID);
        criteria2.andIn("id", roleIds);
        List<Role> roles = roleMapper.selectByCondition(roleCondition);

        if (CollectionUtils.isEmpty(roles) || roles.size() != roleIds.size()) {
            logger.error("角色表数据异常roleIds:{}", roleIds.toString());
        }

        userDO.setRoles(roleFactory.convertRoleList(roles));
        return userDO;
    }

    @Override
    public int countUserByUsername(String username) {
        Condition condition = new Condition(User.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("state", Constant.VALID);
        criteria.andEqualTo("username", username);
        return userMapper.selectCountByCondition(condition);
    }

    private void wrapCreate(User user) {
        LocalDateTime now = LocalDateTime.now();
        user.setGmtUpdate(now);
        user.setGmtCreate(now);
        user.setState(Constant.VALID);
        user.setValid(Constant.VALID);
    }
}
