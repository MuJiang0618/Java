package controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.sun.org.apache.xml.internal.utils.UnImplNode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
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
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.junit.Test;
import service.UserService;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired UserService userService;

    @RequestMapping("succeedRegister")
    public ModelAndView succeedRegister(User user, HttpSession session) {
        ModelAndView mav = new ModelAndView("redirect:enterHomePage");

        user.setUserName(user.getEmailAddress()+"_hello");  // 注册后给一个默认昵称
        userService.doRegister(user);
        System.out.println("一个新用户成功注册");  // 改成第xxx位用户成功注册

        session.setAttribute("userId", user.getUserId());

        return mav;
    }

    @RequestMapping("checkLogin")
    public String checkLogin(String emailAddress, String pwd, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(emailAddress, pwd);
        try {
            subject.login(token);
            int userId = userService.getUserId(emailAddress);
            Session session = subject.getSession();
            session.setAttribute("subject", subject);   // 用于在jsp通过 <c:if test="${empty subject.principal}">判断是否登录
            session.setAttribute("userId", userId);
            model.addAttribute("userData", userService.getUserData(userId));

            return "redirect:enterHomePage";
        } catch (AuthenticationException e) {
            model.addAttribute("error", "验证失败");
            return "redirect:enterDoLogin";
        }
    }

    @RequestMapping("checkRegister")
    @ResponseBody
    public boolean checkRegister(String emailAddress) {
        boolean emailAddressIfExist = userService.checkRegister(emailAddress);

        return emailAddressIfExist;
    }

    @RequestMapping("enterHomePage")
    public ModelAndView enterHomePage(HttpSession session) {
        int userId = (Integer) session.getAttribute("userId");
        UserData userData = userService.getUserData(userId);
        ModelAndView mav = new ModelAndView("home.jsp");

        mav.addObject("userData", userData);
        return mav;
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