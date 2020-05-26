package org.lk.daydayup.service;

import org.apache.solr.client.solrj.SolrServerException;
import org.lk.daydayup.pojo.Item;
import org.lk.daydayup.pojo.Topic;

import java.io.IOException;
import java.util.List;

public interface TopicService {
    List<Topic> search(String topic) throws IOException, SolrServerException;

    Item[] getItemsById(int topicId);

    Topic getTopicById(int topicId);

    void addTopic(Topic topic);
    void addTopicForIndex(Topic topic) throws IOException, SolrServerException;
}
