package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.Blog;

import java.util.ArrayList;

public interface BlogMapper {
    void add(Blog blog);
    void delBlog(int blog_id);
    void edit();

    Blog[] getBlogs(@Param("userId") int userId, @Param("start") int start, @Param("num") int numPerPage);

    int countBlogs(int userId);

    Blog[] getBlogByBlogId(ArrayList<Long> blogId);
}
