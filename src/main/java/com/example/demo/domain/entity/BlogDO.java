package com.example.demo.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-20 09:43
 **/
@Data
public class BlogDO implements Serializable {
    private static final long serialVersionUID = -8020797400652178382L;

    private Integer id;

    private String title;

    private String text;

    private Instant create;
}
