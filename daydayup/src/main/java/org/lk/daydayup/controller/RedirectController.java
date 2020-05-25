package org.lk.daydayup.controller;


import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("")
public class RedirectController {

    @RequestMapping("/topic/add_topic")
    public String addTopic() {
        return "add_topic";
    }
}
