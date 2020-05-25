package org.lk.daydayup.pojo;

import org.apache.solr.client.solrj.beans.Field;

import java.util.List;

// 例如查询"分布式", 则搜索结果页面返回"分布式入门", "分布式进阶"等话题
public class Topic {
    @Field("topic_id") int topicId;
    @Field("topic_name") String name;
    int like = 0;
    int dislike = 0;
    int hot = 0;  // 话题的热门程度, 用于在搜索结果中排序
//    List<Integer> itemIds;

    // 标签


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getLike() {
        return like;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public int getDislike() {
        return dislike;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getHot() {
        return hot;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getTopicId() {
        return topicId;
    }
}
