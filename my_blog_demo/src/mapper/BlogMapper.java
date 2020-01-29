package mapper;

import pojo.Blog;

public interface BlogMapper {
    void add(Blog blog);
    void del(int blog_id);
    void edit();
}
