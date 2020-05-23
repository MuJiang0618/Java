package com.how2java.springboot.controller;

import com.how2java.springboot.mapper.UserMapper;
import com.how2java.springboot.pojo.User;
import com.how2java.springboot.service.UserServiceImpl;
import org.apache.hadoop.fs.Path;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired UserServiceImpl userService;
    @Autowired UserMapper userMapper;
    @Autowired StringRedisTemplate stringRedisTemplate;

    @RequestMapping("succeedRegister")
    public String succeedRegister(User user, HttpServletRequest req) {
        userService.doRegister(user);    // user将自动获得在数据库中的主键id作为其userId属性值

        req.getSession().setAttribute("userId", user.getUserId());
        req.getSession().setAttribute("isLogin", true);

        return "redirect:home";
    }

    // 使用redis缓存session信息, 避免用户重复登录
    @RequestMapping("checkLogin")
    public String checkLogin(String userName, String pwd, HttpServletRequest req) {
        Boolean pass = userService.checkLogin(userName, pwd);
        if(pass) {
            User user = userService.getUserByUserName(userName);
//            req.getSession().setAttribute("userId", user.getUserId());

            stringRedisTemplate.opsForValue().set(req.getRequestedSessionId(), String.valueOf(user.getUserId()));
            return "redirect:/user/home";
        } else
            return "redirect:/index.html";
    }

    @RequestMapping("home")
    public String enterHomePage(HttpServletRequest req, HttpSession session, Model model) {
//        int userId = (Integer) session.getAttribute("userId");
        // 为redis做了修改
        int userId = Integer.valueOf(stringRedisTemplate.opsForValue().get(req.getRequestedSessionId()));
        model.addAttribute("userName", userMapper.getUserName(userId));

        return "home";
    }

    @RequestMapping("logout")
    public String Logout(HttpServletRequest req, HttpServletResponse res) {
//        HttpSession session = req.getSession();
//        session.invalidate();
        stringRedisTemplate.delete(req.getRequestedSessionId());
        return "redirect:/index.html";
    }

    @RequestMapping("uploadProfileImg")
    public String uploadProfileImg() {
        return "uploadProfileImg";
    }


}