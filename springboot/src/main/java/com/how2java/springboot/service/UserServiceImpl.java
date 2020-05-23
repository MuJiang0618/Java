package com.how2java.springboot.service;

import com.how2java.springboot.mapper.UserMapper;
import com.how2java.springboot.pojo.User;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired UserMapper userMapper;

    @Override
    public void doRegister(User user) {
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;   // 使用md5算法加密2次
        String encodedPassword = new SimpleHash("md5", user.getPwd(), salt, times).toString();
        user.setSalt(salt);
        user.setPwd(encodedPassword);
        user.setNickName(user.getUserName() + "_hello");
        userMapper.register(user);
    }

    @Override
    public boolean checkLogin(String userName, String pwd) {
        User user = getUserByUserName(userName);

        int encodeTimes = 2;      // 密码加密次数
        String encodedPwd = new SimpleHash("md5", pwd, user.getSalt(), encodeTimes).toString();
        if(user.getPwd().equals(encodedPwd))
            return true;
        else
            return false;
    }

    @Override
    public boolean checkRegister(String userName) {
        int num = userMapper.checkRegister(userName);   // 同名账号的数量
        if (num > 0)
            return true;
        else
            return false;
    }

    @Override
    public int getUserId(String userName) {
        return userMapper.getUserId(userName);
    }

    @Override
    public String getUserName(int userId) {
        return userMapper.getUserName(userId);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }
}
