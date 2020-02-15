package service.impl;

import mapper.UserMapper;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.User;
import pojo.UserData;
import pojo.UserDataForDAO;
import service.UserService;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.Map;

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
        userMapper.register(user);
        userMapper.addNewUserData(user.getUserId());   // 向表user_data插入新用户的初始信息
    }

    @Override
    public boolean checkLogin(String emailAddress, String pwd) {
        String correctPwd = userMapper.getPwd(emailAddress);
        if(correctPwd == null)  // 账号不存在
            return false;

        if(correctPwd.equals(pwd)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkRegister(String emailAddress) {
        int num = userMapper.checkRegister(emailAddress);   // 同名账号的数量
        if (num > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getUserId(String emailAddress) {
        return userMapper.getUserId(emailAddress);
    }

    @Override
    public UserData getUserData(int userId) {
        UserDataForDAO userDataForDAO = userMapper.getUserData(userId);
        UserData userData = new UserData();
        userData.setUserId(userDataForDAO.getUserId());
        userData.setImgSrc(userDataForDAO.getImgSrc());

        String[] blogCategory = userDataForDAO.getBlogCategory().split("\\|");
        String[] checklist = userDataForDAO.getChecklist().split("\\|");

        userData.setBlogCategory(blogCategory);
        userData.setChecklist(checklist);

        return userData;
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
