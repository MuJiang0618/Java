package com.how2java.springboot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.pojo.Blog;
import com.how2java.springboot.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("blog")
public class BlogController {
    @Autowired BlogService blogService;


    @RequestMapping("addBlog")
    public String addBlog() {
        return "blog/addBlog";
    }

    @RequestMapping("submitBlog")
    @ResponseBody
    public boolean submitBlog(Blog blog, HttpSession session) {
        blog.setAuthorId((Integer) session.getAttribute("userId"));
        blogService.submitBlog(blog);

        return true;
    }

    @RequestMapping("blogDetail")
    public String blogDetail(int blogId, Model model) {
        Blog blog = blogService.getBlog(blogId);
        model.addAttribute("blog", blog);
        return "/blog/blogDetail";
    }

    @RequestMapping("browseMyBlogs")
    public String browseMyBlogs(@RequestParam(value = "start", defaultValue = "0") int start, HttpSession session, Model model) {
        int userId = (Integer) session.getAttribute("userId");
        Blog[] blogs = blogService.listBlogs(userId);
        final int sizePerPage = 10;
        PageHelper.startPage(start, sizePerPage,"createTime desc");

        List<Blog> cs = Arrays.asList(blogs);
        PageInfo<Blog> page = new PageInfo<>(cs);
        model.addAttribute("page", page);

        return "blog/listBlogs";
    }
}
