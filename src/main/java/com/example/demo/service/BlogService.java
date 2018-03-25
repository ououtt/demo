package com.example.demo.service;

import com.example.demo.domain.value.BlogItemValue;
import com.example.demo.repository.orm.Blog;
import com.example.demo.web.dto.BlogSaveDTO;

import java.util.List;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-20 00:08
 **/
public interface BlogService {

    int createBlog(BlogSaveDTO blogSaveDTO, Integer userId);

    List<BlogItemValue> selectBlogItemValuesByUserId(Integer userId);

    Blog showBlogDetail(Integer blogId, Integer userId);

    Blog showBlogDetail(Integer blogId);


    boolean updateBlog(BlogSaveDTO blogSaveDTO, Integer userId);

}
