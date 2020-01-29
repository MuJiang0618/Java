package service.impl;

import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.User;
import pojo.UserData;
import pojo.UserDataForDAO;
import service.UserService;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void doRegister(User user) {
        userMapper.register(user);
        userMapper.addNewUserData(user.getUserId());
    }

    @Override
    public boolean checkLogin(String emailAddress, String pwd) {
        String correctPwd = userMapper.getPwd(emailAddress);
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
}
