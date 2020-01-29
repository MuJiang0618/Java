package controller;

import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.ModelAndView;

import pojo.Blog;
import service.BlogService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;


@Controller
@RequestMapping("")
public class BlogController {
    @Autowired
    BlogService blogService;

    @Test
    public void test() {
        Date time = new Date();
        System.out.println(time);
    }

    @RequestMapping("addBlog")
    public ModelAndView showBlogPage() {
        ModelAndView mav = new ModelAndView("addBlog");
        System.out.println("hello");
        return mav;
    }

    // 博客提交成功后应进入博客查看页面
    @RequestMapping("submitBlog")
    @ResponseBody
    public String addBlog(Blog blog) {
        Date time = blog.getCreate_time();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        blog.setCreate_time(formatter.format(blog.getCreate_time()));
        blogService.add(blog);
//        System.out.println(formatter.format(time));
        System.out.println("成功! 添加时间为: "+ formatter.format(time));

        return "success!";
    }

    // 保存博客草稿
    @RequestMapping("saveSketch")
    @ResponseBody
    public String saveSketch(Blog blog) {
        Date time = blog.getCreate_time();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        blog.setCreate_time(formatter.format(blog.getCreate_time()));
        blogService.add(blog);
//        System.out.println(formatter.format(time));
        System.out.println("成功! 添加时间为: "+ formatter.format(time));

        return "success!";
    }

}