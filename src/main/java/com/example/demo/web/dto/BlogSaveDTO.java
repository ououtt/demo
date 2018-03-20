package com.example.demo.web.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-20 00:06
 **/
@Data
public class BlogSaveDTO {

    private Integer id;

    private String title;

    private String text;

    public boolean check() {
        if (StringUtils.isBlank(text) || StringUtils.isBlank(text)) {
            return false;
        }
        return true;
    }
}
