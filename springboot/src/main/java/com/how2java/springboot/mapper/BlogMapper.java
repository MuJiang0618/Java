package com.how2java.springboot.mapper;

import com.how2java.springboot.pojo.Blog;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogMapper {
    public void submitBlog(Blog blog);

    public void delBlog(int blogId);

    public Blog getBlog(int blogId);

    public Blog[] listBlogs(int userId);
}
