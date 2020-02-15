package controller;

import org.apache.ibatis.annotations.Param;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pojo.Blog;
import pojo.Page;
import service.UserService;
import service.UtilService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

import org.junit.Test;
import sun.awt.image.ImageWatched;

@Controller
@RequestMapping("")
public class UtilController {
    @Autowired UtilService utilService;
    @Autowired UserService userService;

    @RequestMapping("enterUploadProfileImg")
    public ModelAndView enterUploadProfileImg() {
        ModelAndView mav = new ModelAndView("uploadProfileImg.jsp");
        return mav;
    }

    @RequestMapping("uploadProfileImg")
    public ModelAndView uploadProfileImg(HttpServletRequest request, MultipartFile imgFile, HttpSession session)
            throws IllegalStateException, IOException {

        String originalFilename = imgFile.getOriginalFilename();
        String userId = String.valueOf(session.getAttribute("userId"));
        // 头像图片存储在本地用"userId.文件后缀"命名, imgName即保存在数据库中的头像图片名
        String imgName = userId + originalFilename.substring(originalFilename.lastIndexOf("."));
//        File newFile = new File(request.getServletContext().getRealPath("/WEB-INF/img/profile_img"), imgName);
        String imgWarehouse = "D:\\CS\\code\\Java code\\my_blog_img\\profile_img";

        // 删除该用户原头像文件
        File imgDirectory = new File(imgWarehouse);

        try {
            // 遍历寻找原头像文件并删除
            for(File file: imgDirectory.listFiles()) {
                String fileName = file.getName();
                if(fileName.substring(0, fileName.lastIndexOf(".")).equals(userId)) {
                    file.delete();
                    break;
                }
            }
        } catch (NullPointerException exception) {
        }

        File newFile = new File(imgWarehouse, imgName);
        imgFile.transferTo(newFile);

        utilService.updateProfileImg((Integer) session.getAttribute("userId"), imgName);

        ModelAndView mav = new ModelAndView("redirect:enterHomePage");

        return mav;
    }

    @RequestMapping("addToDo")
    @ResponseBody
    public void addToDo(@Param("thing") String thing, HttpSession session) {
        utilService.addToDo((Integer) session.getAttribute("userId"), thing);
    }

    // 实现站内搜索博客功能
    @RequestMapping("search")
    public ModelAndView search(HttpSession session, HttpServletRequest request) throws IOException, SolrServerException {
        int maxSearchRes = 20;     // 搜索结果包含的博客数量最大值
        int numPerPage = 8;  // 搜索结果展示页面每页的博客数
        int userId = (Integer) session.getAttribute("userId");
        int start = 0;
        String prefix = "titleandcontent:";
        if(request.getParameter("start") != null)
            start = Integer.parseInt(request.getParameter("start"));

        Page page = new Page();
        page.setNumPerPage(numPerPage);

        String rawQuery = new String(request.getParameter("query").getBytes("iso-8859-1"), "utf-8");
        ModelAndView mav = new ModelAndView();
        mav.addObject("userData", userService.getUserData(userId));

        if(rawQuery.equals("")) {   // 用户输入为空
            mav.setViewName("home.jsp");
            return mav;
        }

        String query = prefix + rawQuery;
        int totalSearchRes = utilService.getTrueSearchRes(query, maxSearchRes);   // 计算此次检索实际需要呈现到页面的总博客数目
        Blog[] resBlog = utilService.searchBlog(query, start, numPerPage, totalSearchRes);     // 符合条件的搜索返回结果条数可能为0, resBlog可能为null
        page.update(start, totalSearchRes);
//        System.out.println(query);

        mav.setViewName("search.jsp");
        mav.addObject("rawQuery", rawQuery);
        mav.addObject("resBlog", resBlog);
        mav.addObject("page", page);

        return mav;
    }


}
