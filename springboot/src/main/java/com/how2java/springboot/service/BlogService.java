package com.how2java.springboot.service;

import com.how2java.springboot.pojo.Blog;

public interface BlogService {

    public void submitBlog(Blog blog);

    public void delBlog(int blogId);

    public Blog getBlog(int blogId);

    public Blog[] listBlogs(int userId);
}
