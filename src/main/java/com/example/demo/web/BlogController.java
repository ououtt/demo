package com.example.demo.web;

import com.example.demo.domain.entity.UserDO;
import com.example.demo.domain.entity.BlogDO;
import com.example.demo.domain.value.BlogItemValue;
import com.example.demo.factory.BlogFactory;
import com.example.demo.repository.orm.Blog;
import com.example.demo.service.BlogService;
import com.example.demo.web.dto.BlogSaveDTO;
import com.example.demo.web.result.ListResult;
import com.example.demo.web.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-19 23:38
 **/
@RestController
public class BlogController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogFactory blogFactory;

    @RequestMapping(value = "/blog/save", method = RequestMethod.POST)
    public Result<Boolean> blogCreate(BlogSaveDTO blogSaveDTO) {
        if (!blogSaveDTO.check()) {
            logger.error("blogCreate 入参为空");
            return Result.errorResult("创建博客失败");
        }

        UserDO userDO = (UserDO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = userDO.getId();

        Integer id = blogSaveDTO.getId();
        if (id == null) {
            blogService.createBlog(blogSaveDTO, userId);
        } else {
            blogService.updateBlog(blogSaveDTO, userId);
        }

        return Result.successResult(true);
    }

    @RequestMapping(value = "/blogs.do/{id}", method = RequestMethod.GET)
    public ListResult<BlogItemValue> blogs(@PathVariable("id")Integer id) {
        List<BlogItemValue> blogItemValues = blogService.selectBlogItemValuesByUserId(id);
        return ListResult.successResult(blogItemValues);

    }

    @RequestMapping(value = "/blogs.do", method = RequestMethod.GET)
    public ListResult<BlogItemValue> blogs() {
        UserDO userDO = (UserDO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = userDO.getId();
        List<BlogItemValue> blogItemValues = blogService.selectBlogItemValuesByUserId(userId);
        return ListResult.successResult(blogItemValues);

    }

    @RequestMapping(value = "/blog/{blogId}", method = RequestMethod.GET)
    public Result<BlogDO> blogDetail(@PathVariable("blogId") Integer blogId) {
        Blog blog = blogService.showBlogDetail(blogId);
        if (blog == null) {
            return Result.errorResult("查看博客失败");
        }
        return Result.successResult(blogFactory.convertToDO(blog));
    }
}
