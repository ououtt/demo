package com.example.demo.web;

import com.example.demo.constant.RedisKeyConstant;
import com.example.demo.domain.entity.UserDO;
import com.example.demo.service.UserService;
import com.example.demo.web.dto.UserCreateDTO;
import com.example.demo.web.dto.UserDTO;
import com.example.demo.web.result.ListResult;
import com.example.demo.web.result.Result;
import com.google.code.kaptcha.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 13:53
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result<Boolean> register(UserCreateDTO userCreateDTO, HttpServletRequest request) {
        if (userCreateDTO == null || !userCreateDTO.check()) {
            logger.error("register 入参失败");
            return Result.errorResult("入参检验错误");
        }
        //验证码校验
        HttpSession session = request.getSession();
        String realCaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String captcha = userCreateDTO.getKaptcha();
        if (!captcha.equalsIgnoreCase(realCaptcha)) {
            logger.error("验证码错误:{},{}", realCaptcha, captcha);
            return Result.errorResult("验证码错误");
        }

        String username = userCreateDTO.getUsername();
        String registerKey = RedisKeyConstant.REGITER + username;
        try {
            Long registerCount = stringRedisTemplate.opsForValue().increment(registerKey, 1);
            if (registerCount != null && registerCount > 1) {
                logger.error("redis key已存在:{}", registerKey);
                return Result.errorResult("请勿重复提交");
            }

            //查找当前是否有同名用户已存在
            int count = userService.countByUsername(username);
            if (count > 0) {
                logger.error("username:{},count:{}", username, count);
                return Result.errorResult("用户名已存在");
            }
            UserDO userDO = wrapUserCreateDTO(userCreateDTO);
            int userId = userService.createCommonUser(userDO);
            userDO.setId(userId);
            return Result.successResult(Boolean.TRUE);
        } finally {
            stringRedisTemplate.delete(registerKey);
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ListResult<UserDTO> users() {
        return ListResult.successResult(userService.selectAll());
    }


    private UserDO wrapUserCreateDTO(UserCreateDTO userCreateDTO) {
        UserDO userDO = new UserDO();
        userDO.setAddress(userCreateDTO.getAddress());
        userDO.setCityCode(userCreateDTO.getCityCode());
        userDO.setEmail(userCreateDTO.getEmail());
        userDO.setIdCard(userCreateDTO.getIdCard());
        userDO.setImages(userCreateDTO.getImages());
        userDO.setMobilePhone(userCreateDTO.getMobilePhone());
        userDO.setPassword(userCreateDTO.getPassword());
        userDO.setSex(userCreateDTO.getSex());
        userDO.setUsername(userCreateDTO.getUsername());
        return userDO;
    }
}
