package service;

import pojo.Blog;

public interface BlogService {
    void add(Blog blog);
    void del(int blog_id);
    void edit();
}
