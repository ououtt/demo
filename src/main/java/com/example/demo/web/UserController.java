package com.example.demo.web;

import com.example.demo.domain.entity.UserDO;
import com.example.demo.service.UserService;
import com.example.demo.web.dto.UserCreateDTO;
import com.example.demo.web.result.Result;
import com.google.code.kaptcha.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 13:53
 **/
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(UserCreateDTO userCreateDTO, HttpServletRequest request) {
        if (userCreateDTO == null || !userCreateDTO.check()) {
            logger.error("register 入参失败");
            return "/register.html";
        }
        //验证码校验
        HttpSession session = request.getSession();
        String realCaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String captcha = userCreateDTO.getKaptcha();
        if (!captcha.equalsIgnoreCase(realCaptcha)) {
            logger.error("验证码错误:{},{}", realCaptcha, captcha);
            return "/register.html";
        }

//        try {
//            String registerKey = RedisKeyConstant.REGITER_KEY + userCreateDTO.getUsername();
//            Long registerCount = stringRedisTemplate.opsForValue().increment(registerKey, 1);
//        }

        UserDO userDO = wrapUserCreateDTO(userCreateDTO);
        int userId = userService.createCommonUser(userDO);
        userDO.setId(userId);
        return "/blogs.html";
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
