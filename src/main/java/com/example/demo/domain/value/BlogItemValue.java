package com.example.demo.domain.value;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-20 09:47
 **/
@Data
public class BlogItemValue implements Serializable {
    private static final long serialVersionUID = 4033975317766566732L;

    private Integer id;

    private String title;

    private LocalDateTime create;
}
