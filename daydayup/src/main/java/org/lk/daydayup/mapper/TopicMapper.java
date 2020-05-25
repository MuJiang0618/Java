package org.lk.daydayup.mapper;

import org.lk.daydayup.pojo.Item;
import org.lk.daydayup.pojo.Topic;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicMapper {

    Topic[] getTopicsByIds(List<Integer> topicIds);

    Item[] getItemsByTopicId(int topicId);

    Topic getTopicById(int topicId);

    void addTopic(Topic topic);
}
