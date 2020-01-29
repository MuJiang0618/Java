package controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.sun.org.apache.xml.internal.utils.UnImplNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.ModelAndView;

import pojo.User;
import pojo.UserData;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.junit.Test;
import service.UserService;


@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    UserService userService;

    @Test
    public void test() {
        String s = "NLP|ML|机器学习";
        String[] a = s.split("\\|");
        for(String b : a)
            System.out.println(b);
    }

    @RequestMapping("succeedRegister")
    public ModelAndView succeedRegister(User user) {
        ModelAndView mav = new ModelAndView("home.jsp");

        user.setUserName(user.getEmailAddress()+"_hello");  // 注册后给一个默认昵称
        userService.doRegister(user);
        System.out.println("一个新用户成功注册");  // 改成第xxx位用户成功注册
        // 数据库中字段值为null, getUserData()会报空指针异常
        UserData userData = userService.getUserData(user.getUserId());
        mav.addObject("imgSrc", userData.getImgSrc());

        return mav;
    }

//    @RequestMapping("succeedLogin")
//    public ModelAndView succeedLogin(User user) {
//        ModelAndView mav = new ModelAndView("home.html");
//
//        user.setUserName(user.getEmailAddress()+"_hello");  // 注册后给一个默认昵称
//        int userId = userService.getUserId(user.getEmailAddress());
//        UserData userData = userService.getUserData(userId);
//        mav.addObject("userData", userData);
//        System.out.println("一位新用户成功登陆");
//
//        return mav;
//    }

    @RequestMapping("checkLogin")
    public ModelAndView checkLogin(String emailAddress, String pwd) {
        ModelAndView mav = new ModelAndView();
        boolean ifPwdCorrect = userService.checkLogin(emailAddress, pwd);
        if (ifPwdCorrect) {
            mav.setViewName("home.jsp");
            int userId = userService.getUserId(emailAddress);
            UserData userData = userService.getUserData(userId);

            mav.addObject("imgSrc", userData.getImgSrc());
            mav.addObject("userData", userData);

            System.out.println("一位用户成功登陆");

            return mav;
        } else {
            mav.setViewName("login.html");
            return mav;
        }
    }

    @RequestMapping("checkRegister")
    @ResponseBody
    public boolean checkRegister(String emailAddress) {
        boolean emailAddressIfExist = userService.checkRegister(emailAddress);

        return emailAddressIfExist;
    }

    @RequestMapping("enterDoLogin")
    public ModelAndView enterLogin() {
        ModelAndView mav = new ModelAndView("login.html");
        return mav;
    }

    @RequestMapping("enterDoRegister")
    public ModelAndView enterRegister() {
        ModelAndView mav = new ModelAndView("register.html");
        return mav;
    }

}