package com.example.demo.repository;

import com.example.demo.domain.value.BlogItemValue;
import com.example.demo.repository.orm.Blog;
import com.example.demo.web.dto.BlogSaveDTO;

import java.util.List;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-20 00:05
 **/
public interface BlogRepository {

    int createBlog(Blog blog);

    List<BlogItemValue> selectBlogItemValuesByUserId(Integer userId);

    Blog selectById(Integer id);

    boolean updateById(BlogSaveDTO blogSaveDTO);

    boolean updateBlog(BlogSaveDTO blogSaveDTO, Integer userId);
}
