package org.lk.daydayup.controller;


import org.lk.daydayup.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class RedirectController {
    @Autowired TopicService topicService;

    @RequestMapping("/topic/add_topic")
    public String addTopic() {
        return "add_topic";
    }

    @RequestMapping("/item/add_item")
    public String addItem(int topicId, Model model) {
        model.addAttribute("topicId", topicId);
//        model.addAttribute("topic", topicService.getTopicById(topicId));
        return "add_item";
    }
}
