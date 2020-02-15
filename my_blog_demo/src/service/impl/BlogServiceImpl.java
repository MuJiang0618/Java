package service.impl;

import mapper.BlogMapper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Blog;
import service.BlogService;

import java.io.IOException;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired BlogMapper blogMapper;

    public static SolrClient client;
    private static String url;
    static {
        url = "http://localhost:8983/solr/my_blog";
        client = new HttpSolrClient.Builder(url).build();
    }

    @Override
    public void add(Blog blog) throws IOException, SolrServerException {
        blogMapper.add(blog);
    }

    // 提交博客的同时把博客信息上传到Solr服务器以供搜索
    @Override
    public void addIndex(Blog blog) throws IOException, SolrServerException {
        DocumentObjectBinder binder = new DocumentObjectBinder();
        SolrInputDocument doc = binder.toSolrInputDocument(blog);   // 由于数据库blog表字段和solr中blog的字段不一致, 会出错
        client.add(doc);
        client.commit();
    }

    @Override
    public void saveAsSketch(Blog blog) {

    }

    @Override
    public void delBlog(int blog_id) {
        blogMapper.delBlog(blog_id);
    }

    @Override
    public void edit() {

    }

    @Override
    public Blog[] getBlogs(int userId, int start, int numPerPage) {
        Blog[] blogs = blogMapper.getBlogs(userId, start, numPerPage);

        return blogs;
    }

    @Override
    public int countBlogs(int userId) {
        int numBlogs = blogMapper.countBlogs(userId);
        return numBlogs;
    }

}
