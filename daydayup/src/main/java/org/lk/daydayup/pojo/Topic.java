package org.lk.daydayup.pojo;

import org.apache.solr.client.solrj.beans.Field;

import java.util.List;

// 例如查询"分布式", 则搜索结果页面返回"分布式入门", "分布式进阶"等话题
public class Topic {
    @Field("topic_id") int topicId;
    @Field("topic_name") String name;
    @Field("likes") int likes = 0;
    @Field("dislikes") int dislikes = 0;
    @Field("hot") int hot = 0;  // 话题的热门程度, 用于在搜索结果中排序
//    List<Integer> itemIds;

    // 标签

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLikes(int like) {
        this.likes = like;
    }

    public int getLikes() {
        return likes;
    }

    public void setDislikes(int dislike) {
        this.dislikes = dislike;
    }

    public int getDislikes() {
        return dislikes;
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
