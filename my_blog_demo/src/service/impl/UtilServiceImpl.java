package service.impl;

import mapper.BlogMapper;
import mapper.ChecklistMapper;
import mapper.UtilMapper;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Blog;
import service.UtilService;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;
import java.util.*;

@Service
public class UtilServiceImpl implements UtilService {
    @Autowired UtilMapper utilMapper;
    @Autowired ChecklistMapper checklistMapper;
    @Autowired BlogMapper blogMapper;

    public static SolrClient client;
    private static String url;
    static {
        url = "http://localhost:8983/solr/my_blog";   // sorl服务器地址
        client = new HttpSolrClient.Builder(url).build();
    }

    public void updateProfileImg(int userId, String imgName) {
        utilMapper.updateProfileImg(userId, imgName);
    }

    @Override
    public void addToDo(int userId, String thing) {
        String checklist = checklistMapper.getToDo(userId);
        String res;
        LinkedList<String> s = new LinkedList<String>();;

        if(checklist.length() == 0) {

            s.addLast(thing);
            res = String.join("|", s);
        } else {
            String[] s1 = checklist.split("\\|");
            s.addAll(Arrays.asList(s1));
            s.addLast(thing);
            res = String.join("|", s);
        }

        checklistMapper.addToDo(userId, res);
        System.out.println("更新checklist成功");
    }

    @Override
    public Blog[] searchBlog(String keyword, int start, int numPerPage, int totalSearchRes) throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setStart(start);  // 查询结果分页时要更新start
        numPerPage = ((start+numPerPage) > totalSearchRes) ? (totalSearchRes - start) : numPerPage;
        query.setRows(numPerPage);  // 每页的条目数
//        query.setParam("sort", "like", "desc")    // 根据点赞数排序结果
        query.setQuery(keyword);
        QueryResponse rsp = client.query(query);
        SolrDocumentList documents= rsp.getResults();   // 查询的结果列表

        if(documents.getNumFound() == 0)  // 返回0条符合条件的记录
            return null;

        ArrayList<Long> resBlogId = new ArrayList<Long>();
        for(SolrDocument doc : documents) {
            Long blog_id = (Long) doc.getFieldValue("blog_id");
            resBlogId.add(blog_id);
        }

        // 根据搜索结果返回的博客的id从数据库取出博客
        Blog[] resBlog = blogMapper.getBlogByBlogId(resBlogId);

        return resBlog;
    }

    @Override
    public int getTrueSearchRes(String keyword, int maxSearchRes) throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setStart(0);  // 查询结果分页时要更新start
        query.setRows(maxSearchRes);  // 每页的条目数
        query.setQuery(keyword);
        QueryResponse rsp = client.query(query);
        SolrDocumentList documents= rsp.getResults();

        return Math.min((int) documents.getNumFound(), maxSearchRes);
    }
}
