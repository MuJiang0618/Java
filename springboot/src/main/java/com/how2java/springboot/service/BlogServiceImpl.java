package com.how2java.springboot.service;

import com.how2java.springboot.mapper.BlogMapper;
import com.how2java.springboot.mapper.UserMapper;
import com.how2java.springboot.pojo.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired BlogMapper blogMapper;
    @Autowired UserMapper userMapper;

    @Override
    public void submitBlog(Blog blog) {
        String authorName = userMapper.getNicknameById(blog.getAuthorId());
        blog.setAuthorName(authorName);
        blogMapper.submitBlog(blog);
    }

    @Override
    public void delBlog(int blogId) {
        blogMapper.delBlog(blogId);
    }

    @Override
    public Blog getBlog(int blogId) {
        return blogMapper.getBlog(blogId);
    }

    @Override
    public Blog[] listBlogs(int userId) {
        return blogMapper.listBlogs(userId);
    }
}
