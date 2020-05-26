package org.lk.daydayup.controller;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.lk.daydayup.mapper.TopicMapper;
import org.lk.daydayup.pojo.Item;
import org.lk.daydayup.pojo.Topic;
import org.lk.daydayup.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
如何对搜索结果排序?
①
 */

@RestController
@RequestMapping("/topics")
public class TopicController {

    public static SolrClient client;
    private static String url;
    static {
        url = "http://localhost:8983/solr/daydayup";
        client = new HttpSolrClient.Builder(url).build();
    }

    @Autowired TopicService topicService;

//    @GetMapping("")
//    public ModelAndView searchTopic(String query, ModelAndView mav) throws IOException, SolrServerException {
//        // 查询Solr
//        List<Topic> topics = topicService.search(query);
//        mav.setViewName("search_result");
//        mav.addObject("topics", topics);   // 用于在搜索页面列出相关topic的信息
//        return mav;
//    }

    // 返回1个topic的详情页
    @GetMapping("{topicId}")
    public ModelAndView topicDetail(@PathVariable("topicId") int topicId, ModelAndView mav) {
        mav.setViewName("topic_detail");
        Item[] items = topicService.getItemsById(topicId);
        if(items != null)
            mav.addObject("items", items);
        mav.addObject("topic", topicService.getTopicById(topicId));

        return mav;
    }

    // 表单中向topic注入name
    @PostMapping("")
    public void SubmitAddTopic(Topic topic, HttpServletResponse response) throws IOException, SolrServerException {
        // 数据库
        topicService.addTopic(topic);
        topicService.addTopicForIndex(topic);   //  添加索引到Solr

        // 重定向到该新建的topic详情页
        response.sendRedirect("/topics/" + topic.getTopicId());
    }

    public void delTopic() {

    }

}
