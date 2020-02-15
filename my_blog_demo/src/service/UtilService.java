package service;

import org.apache.solr.client.solrj.SolrServerException;
import pojo.Blog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface UtilService {

    void updateProfileImg(int userId, String imgName);

    void addToDo(int userId, String thing);

    Blog[] searchBlog(String keyword, int start, int numPerPage, int totalSearchRes) throws IOException, SolrServerException;

    int getTrueSearchRes(String query, int maxSearchRes) throws IOException, SolrServerException;

}
