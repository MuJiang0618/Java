package com.how2java.springboot.service;

import com.how2java.springboot.pojo.User;

public interface UserService {
    void doRegister(User user);

    boolean checkLogin(String userName, String pwd);

    boolean checkRegister(String userName);

    int getUserId(String userName);

    String getUserName(int userId);

    User getUserByUserName(String userName);
}
