package org.lk.daydayup.controller;

import org.apache.solr.client.solrj.SolrServerException;
import org.lk.daydayup.pojo.Item;
import org.lk.daydayup.pojo.ItemUtil;
import org.lk.daydayup.service.ItemService;
import org.lk.daydayup.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
如何对搜索结果排序?
①
 */

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired ItemService itemService;

    // 添加item
    @PostMapping("add_item/{topicId}")
    public void SubmitAddItem(@PathVariable("topicId") int topicId, ItemUtil itemUtil, HttpServletResponse response) throws IOException, SolrServerException {
        // 数据库
        Item item = new Item();
        item.setIntro(itemUtil.getIntro());
        item.setTopicId(topicId);
        item.setTitle(itemUtil.getTitle());
        item.setUrl(itemUtil.getUrl());
//        item.setTitle(itemUtil.getTitle);
        itemService.addItem(item);

        // 重定向到该新建的topic详情页
        response.sendRedirect("/topics/" + item.getTopicId());
    }

    public void delTopic() {

    }

    @PostMapping("give_like")
    @ResponseBody
    public void like(int itemId) {
        itemService.giveLike(itemId);
    }

}