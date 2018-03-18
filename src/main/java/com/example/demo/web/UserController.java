package com.example.demo.web;

import com.example.demo.domain.entity.UserDO;
import com.example.demo.service.UserService;
import com.example.demo.web.dto.UserCreateDTO;
import com.example.demo.web.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result<UserDO> register(UserCreateDTO userCreateDTO) {
        if (userCreateDTO == null || !userCreateDTO.check()) {
            logger.error("register 入参失败");
            return Result.errorResult("缺少注册必传参数!");
        }
        UserDO userDO = wrapUserCreateDTO(userCreateDTO);
        int userId = userService.createComonUser(userDO);
        userDO.setId(userId);
        return Result.successResult(userDO);
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
