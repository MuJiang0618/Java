package org.lk.daydayup.service;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.lk.daydayup.mapper.TopicMapper;
import org.lk.daydayup.pojo.Item;
import org.lk.daydayup.pojo.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    public static SolrClient client;
    int START = 0;
    int NUM_PER_PAGE = 10;
    private static String url;

    static {
        url = "http://localhost:8983/solr/my_blog";   // sorl服务器地址
        client = new HttpSolrClient.Builder(url).build();
    }

    @Autowired TopicMapper topicMapper;

    @Override
    public Topic[] search(String topic) throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setStart(START);  // 查询结果分页时要更新start
        query.setRows(NUM_PER_PAGE);  // 每页的条目数
//        query.setParam("sort", "like", "desc")    // 根据点赞数排序结果
        query.setQuery(topic);
        QueryResponse rsp = client.query(query);
        SolrDocumentList documents= rsp.getResults();   // 查询的结果列表
        List<Integer> topicIds = new ArrayList<>();
        for(SolrDocument doc : documents)
            topicIds.add((Integer) doc.getFieldValue("id"));  // topic的id

        Topic[] resultSet = topicMapper.getTopicsByIds(topicIds);
        return resultSet;
    }

    @Override
    public Item[] getItemsById(int topicId) {
        return topicMapper.getItemsByTopicId(topicId);
    }

    @Override
    public Topic getTopicById(int topicId) {
        return topicMapper.getTopicById(topicId);
    }

    @Override
    public void addTopic(Topic topic) {
        topicMapper.addTopic(topic);
    }
}
