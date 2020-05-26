package org.lk.daydayup.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class RedirectController {

    @RequestMapping("/topic/add_topic")
    public String addTopic() {
        return "add_topic";
    }
}
