package com.example.demo.domain.entity;

import com.example.demo.domain.value.RoleValue;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-17 18:50
 **/
@Data
public class UserDO implements Serializable{

    private static final long serialVersionUID = 9149804964192890060L;
    private Integer id;

    private String username;

    private String mobilePhone;

    private String email;

    private String password;

    private Integer sex;

    private String idCard;

    private String images;

    private Integer cityCode;

    private String address;

    private Integer state;

    private Date create;

    private Integer valid;

    private List<RoleValue> roles;
}
