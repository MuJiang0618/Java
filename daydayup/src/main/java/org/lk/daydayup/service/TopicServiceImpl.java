package org.lk.daydayup.service;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
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
        http://127.0.0.1:8983/solr/#/
        url = "http://localhost:8983/solr/daydayup";   // sorl服务器地址
        client = new HttpSolrClient.Builder(url).build();
    }

    @Autowired TopicMapper topicMapper;

    @Override
    public List<Topic> search(String topic) throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setStart(START);  // 查询结果分页时要更新start
        query.setRows(NUM_PER_PAGE);  // 每页的条目数
//        query.setParam("sort", "like", "desc")    // 根据点赞数排序结果
        query.setQuery("topic_name:" + topic);
//        solrQuery.setSort("onlineTime", ORDER.desc);
        QueryResponse resp = client.query(query);
        List<Topic> topics = resp.getBeans(Topic.class);

        return topics;
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

    @Override
    public void addTopicForIndex(Topic topic) throws IOException, SolrServerException {
        // 添加索引到Solr
        DocumentObjectBinder binder = new DocumentObjectBinder();
        SolrInputDocument doc = binder.toSolrInputDocument(topic);
        client.add(doc);
        client.commit();
    }
}
