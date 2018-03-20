package com.example.demo.factory.impl;

import com.example.demo.domain.entity.BlogDO;
import com.example.demo.factory.BlogFactory;
import com.example.demo.repository.orm.Blog;
import org.springframework.stereotype.Component;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-20 10:10
 **/
@Component
public class BlogFactoryImpl implements BlogFactory {
    @Override
    public BlogDO convertToDO(Blog blog) {
        BlogDO blogDO = new BlogDO();
        blogDO.setCreate(blog.getGmtCreate());
        blogDO.setId(blog.getId());
        blogDO.setText(blog.getText());
        blogDO.setTitle(blog.getTitle());
        return blogDO;
    }
}
