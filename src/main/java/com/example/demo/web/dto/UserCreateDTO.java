package com.example.demo.web.dto;

import com.example.demo.constant.enums.SexEnum;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 22:03
 **/
@Data
public class UserCreateDTO {

    private String username;

    private String mobilePhone;

    private String email;

    private String password;

    private Integer sex;

    private String idCard;

    private String images;

    private Integer cityCode;

    private String address;

    private String kaptcha;

    public boolean check() {
        if (StringUtils.isBlank(username)) {
            return false;
        }
        if (StringUtils.isBlank(password)) {
            return false;
        }
        if (SexEnum.parseId(sex) == null) {
            return false;
        }
        if (StringUtils.isBlank(kaptcha)) {
            return false;
        }
        return true;
    }
}
