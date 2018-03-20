package com.example.demo.factory;

import com.example.demo.domain.entity.BlogDO;
import com.example.demo.repository.orm.Blog;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-20 10:10
 **/
public interface BlogFactory {

    BlogDO convertToDO(Blog blog);
}
