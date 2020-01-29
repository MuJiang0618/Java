package service.impl;

import mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Blog;
import service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogMapper blogMapper;

    @Override
    public void add(Blog blog) {
        blogMapper.add(blog);
    }

    @Override
    public void del(int blog_id) {
        blogMapper.del(blog_id);
    }

    @Override
    public void edit() {

    }
}
