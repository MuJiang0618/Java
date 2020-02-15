package service;

import org.apache.solr.client.solrj.SolrServerException;
import pojo.Blog;

import java.io.IOException;

public interface BlogService {
    void add(Blog blog) throws IOException, SolrServerException;
    void delBlog(int blog_id);
    void edit();

    Blog[] getBlogs(int userId, int start, int numPerPage);

    int countBlogs(int userId);

    void addIndex(Blog blog) throws IOException, SolrServerException;

    void saveAsSketch(Blog blog);
}
