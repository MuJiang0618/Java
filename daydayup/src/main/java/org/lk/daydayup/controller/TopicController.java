package org.lk.daydayup.controller;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.lk.daydayup.mapper.TopicMapper;
import org.lk.daydayup.pojo.Topic;
import org.lk.daydayup.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/*
如何对搜索结果排序?
①
 */

@RestController
@RequestMapping("/topic")
public class TopicController {

    public static SolrClient client;
    private static String url;
    static {
        url = "http://localhost:8983/solr/my_blog";
        client = new HttpSolrClient.Builder(url).build();
    }

    @Autowired TopicService topicService;

//    @GetMapping("")
//    @ResponseBody
//    public String test0() {
//        System.out.println("shide");
//        return "asd";
//    }
//
//    @GetMapping("{id}")
//    @ResponseBody
//    public String test(@PathVariable("id") String id) {
//        System.out.println(id);
//        return "asd";
//    }

    // 返回1个topic的详情页
    // /topic/12345
    @GetMapping("{topicId}")
    public String topicDetail(@PathVariable("topicId") int topicId, Model model) {
        model.addAttribute("items", topicService.getItemsById(topicId));
        model.addAttribute("topic", topicService.getTopicById(topicId));

        return "topic_detail";
    }

    // 表单中向topic注入name
    @PostMapping("")
    public String SubmitAddTopic(@RequestBody Topic topic) throws IOException, SolrServerException {
        // 数据库
        topicService.addTopic(topic);

        // 添加索引到Solr
        DocumentObjectBinder binder = new DocumentObjectBinder();
        SolrInputDocument doc = binder.toSolrInputDocument(topic);
        client.add(doc);
        client.commit();


        // 重定向到该新建的topic详情页
        return "redirect:/topic/" + String.valueOf(topic.getTopicId());
    }

    public void delTopic() {

    }

}
