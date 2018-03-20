package com.example.demo.service.impl;

import com.example.demo.constant.Constant;
import com.example.demo.domain.value.BlogItemValue;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.orm.Blog;
import com.example.demo.service.BlogService;
import com.example.demo.web.dto.BlogSaveDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-20 00:08
 **/
@Service
public class BlogServiceImpl implements BlogService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public int createBlog(BlogSaveDTO blogSaveDTO, Integer userId) {
        Blog blog = wrap(blogSaveDTO);
        blog.setUserId(userId);
        return blogRepository.createBlog(blog);
    }

    @Override
    public List<BlogItemValue> selectBlogItemValuesByUserId(Integer userId) {
        return blogRepository.selectBlogItemValuesByUserId(userId);
    }

    @Override
    public Blog showBlogDetail(Integer blogId, Integer userId) {
        Blog blog = blogRepository.selectById(blogId);
        if (blog == null) {
            return null;
        }
        if (!blog.getUserId().equals(userId)) {
            logger.error("不能查看非本人创建的博客,blogId:{},userId:{}", blogId, userId);
            return null;
        }
        return blog;
    }

    @Override
    public boolean updateBlog(BlogSaveDTO blogSaveDTO, Integer userId) {
        return blogRepository.updateBlog(blogSaveDTO, userId);
    }

    private Blog wrap(BlogSaveDTO blogSaveDTO) {
        Blog blog = new Blog();
        blog.setText(blogSaveDTO.getText());
        blog.setTitle(blogSaveDTO.getTitle());
        blog.setState(Constant.VALID);
        return blog;
    }
}
