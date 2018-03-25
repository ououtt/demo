package com.example.demo.web.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-25 10:06
 **/
@Data
public class UserDTO implements Serializable {

    private Integer id;

    private String username;

    private String sex;

    private String create;
}
