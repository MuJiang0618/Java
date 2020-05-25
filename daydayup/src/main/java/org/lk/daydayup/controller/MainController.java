package org.lk.daydayup.controller;

import org.apache.solr.client.solrj.SolrServerException;
import org.lk.daydayup.pojo.Topic;
import org.lk.daydayup.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class MainController {
    @Autowired TopicService topicService;

    @RequestMapping("/search")
    public String search(String topic, Model model) throws IOException, SolrServerException {
        // 查询Solr
        Topic[] topics = topicService.search(topic);
        model.addAttribute("topics", topics);   // 用于在搜索页面列出相关topic的信息

        return "search_result";
    }



}
