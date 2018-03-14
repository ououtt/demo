package com.example.demo.web;

import com.example.demo.repository.mapper.UserMapper;
import com.example.demo.repository.orm.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-13 23:29
 **/
@RestController
public class TestController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/redis", method = RequestMethod.GET)
    public long testRedis() {
        return stringRedisTemplate.opsForValue().increment("key", 1);
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public List<User> testSql() {
        Condition condition = new Condition(User.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("state", 1);
        return userMapper.selectByCondition(condition);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public User testSql2() {
        logger.debug("-------------debug");
        logger.info("-------------info");
        logger.warn("-------------warn");
        logger.error("--------------error");
        User user = new User();
        user.setState(1);
        user.setPassword("aaaa");
        user.setUsername("qeqqq");
        userMapper.insert(user);
        return user;
    }
}
