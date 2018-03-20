package com.example.demo.repository.mapper;

import com.example.demo.domain.value.BlogItemValue;
import com.example.demo.repository.orm.Blog;

import java.util.List;

public interface BlogMapper extends MyMapper<Blog> {

    List<BlogItemValue> selectBlogItemValuesByUserId(Integer userId);
}