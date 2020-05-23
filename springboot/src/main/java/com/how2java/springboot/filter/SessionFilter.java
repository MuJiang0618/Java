package com.how2java.springboot.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebFilter("/*")
@Order(1)
public class SessionFilter implements Filter {
    @Autowired StringRedisTemplate stringRedisTemplate;

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String url = req.getRequestURI();
        if (url.endsWith("index.html") || url.endsWith("register.html") || url.endsWith("/user/checkLogin") || url.endsWith("/user/succeedRegister")) {
            chain.doFilter(req, res);
            return;
        }

//        HttpSession session = req.getSession();
//        if(session.getAttribute("userId") == null) {
//            res.sendRedirect("/index.html");
//            return;
//        }

        // 使用redis缓存session
        if(stringRedisTemplate.opsForValue().get(req.getRequestedSessionId()) == null) {
            res.sendRedirect("/index.html");
            return;
        }

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}