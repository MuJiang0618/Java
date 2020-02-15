package controller;

import com.alibaba.druid.support.json.JSONUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.ModelAndView;

import pojo.Blog;
import pojo.Page;
import pojo.UserData;
import service.BlogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import service.UserService;

@Controller
@RequestMapping("")
public class BlogController {
    @Autowired BlogService blogService;
    @Autowired UserService userService;

    @RequestMapping("addBlog")
    public ModelAndView editBlogPage() {
        ModelAndView mav = new ModelAndView("addBlog.html");
        return mav;
    }

    // 博客提交成功后应进入博客查看页面
    @RequestMapping("submitBlog")
    @ResponseBody
    public String submitBlog(Blog blog, HttpSession session) throws IOException, SolrServerException {
        int userId = (Integer) session.getAttribute("userId");
        blog.setAuthor_id(userId);
        blog.setAuthor_name(userService.getUserName(userId));

        blogService.add(blog);
        blogService.addIndex(blog);  // 将该博客加入solr索引

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
        blogService.saveAsSketch(blog);
//        System.out.println(formatter.format(time));
        System.out.println("成功! 添加时间为: "+ formatter.format(time));

        return "success!";
    }

    // 浏览用户的所有博客
    @RequestMapping("browseBlogs")
    public ModelAndView browseBlogs(HttpSession session, HttpServletRequest request) {
        int userId = (Integer) session.getAttribute("userId");
        Page page = new Page();

        // 当浏览器没有传参数start时
        try {
            page.setStart(Integer.parseInt(request.getParameter("start")));
        } catch (NumberFormatException e) {
        }

        Blog[] blogs = blogService.getBlogs(userId, page.getStart(), page.getNumPerPage());
        UserData userData = userService.getUserData(userId);
        int numBlog = blogService.countBlogs(userId);

        page.update(page.getStart(), numBlog);

        ModelAndView mav = new ModelAndView("browseBlogs.jsp");
        mav.addObject("page", page);
        mav.addObject("blogs", blogs);
        mav.addObject("userData", userData);

        return mav;
    }

    @RequestMapping("delBlog")
    @ResponseBody
    public void delBlog(int blogId) {
        blogService.delBlog(blogId);
    }

}