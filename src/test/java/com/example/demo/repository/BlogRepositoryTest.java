package com.example.demo.repository;

import com.example.demo.DemoApplicationTests;
import com.example.demo.repository.orm.Blog;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

import static org.junit.Assert.*;

public class BlogRepositoryTest extends DemoApplicationTests {

    @Autowired
    BlogRepository blogRepository;

    @Test
    public void createBlog() {
        Blog blog = new Blog();
        blog.setText("asdf");
        blog.setTitle("rrrr");
        blog.setUserId(333);
        blog.setState(1);
        Instant now = Instant.now();
        blog.setGmtCreate(now);
        blog.setGmtUpdate(now);
        int i = blogRepository.createBlog(blog);
        Assert.assertEquals(1, i);
    }

    @Test
    public void selectBlogItemValuesByUserId() {
    }

    @Test
    public void selectById() {
    }

    @Test
    public void updateById() {
    }

    @Test
    public void updateBlog() {
    }
}