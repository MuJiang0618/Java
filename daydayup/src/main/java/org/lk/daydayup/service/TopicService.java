package org.lk.daydayup.service;

import org.apache.solr.client.solrj.SolrServerException;
import org.lk.daydayup.pojo.Item;
import org.lk.daydayup.pojo.Topic;

import java.io.IOException;

public interface TopicService {
    Topic[] search(String topic) throws IOException, SolrServerException;

    Item[] getItemsById(int topicId);

    Topic getTopicById(int topicId);

    void addTopic(Topic topic);
}
