package com.example.demo.repository.impl;

import com.example.demo.constant.Constant;
import com.example.demo.domain.value.BlogItemValue;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.mapper.BlogMapper;
import com.example.demo.repository.orm.Blog;
import com.example.demo.web.dto.BlogSaveDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-20 00:05
 **/
@Repository
public class BlogRepositoryImpl implements BlogRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public int createBlog(Blog blog) {
        wrap(blog);
        int insertCount = blogMapper.insertSelective(blog);
        return blog.getId();
    }

    @Override
    public List<BlogItemValue> selectBlogItemValuesByUserId(Integer userId) {
        if (userId == null) {
            logger.error("selectBlogItemValuesByUserId 入参为空");
            return null;
        }
        return blogMapper.selectBlogItemValuesByUserId(userId);
    }

    @Override
    public Blog selectById(Integer id) {
        if (id == null) {
            logger.error("selectById 入参为空");
            return null;
        }
        return blogMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateById(BlogSaveDTO blogSaveDTO) {
        Blog blog = wrap(blogSaveDTO);
        int updateCount = blogMapper.updateByPrimaryKeySelective(blog);
        if (updateCount != 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateBlog(BlogSaveDTO blogSaveDTO, Integer userId) {
        Condition condition = new Condition(Blog.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("id", blogSaveDTO.getId());
        criteria.andEqualTo("state", Constant.VALID);
        int updateCount = blogMapper.updateByConditionSelective(wrap(blogSaveDTO), condition);
        if (updateCount != 1) {
            return false;
        }
        return true;
    }

    private Blog wrap(BlogSaveDTO blogSaveDTO) {
        Blog blog = new Blog();
        blog.setId(blogSaveDTO.getId());
        blog.setTitle(blogSaveDTO.getTitle());
        blog.setText(blogSaveDTO.getText());
        return blog;
    }

    private void wrap(Blog blog) {
        LocalDateTime now = LocalDateTime.now();
        blog.setGmtCreate(now);
        blog.setGmtUpdate(now);
    }
}
