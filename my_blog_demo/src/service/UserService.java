package service;

import pojo.User;
import pojo.UserData;

import java.util.Map;

public interface UserService {
    void doRegister(User user);

    boolean checkLogin(String emailAddress, String pwd);

    boolean checkRegister(String emailAddress);

    int getUserId(String emailAddress);

    UserData getUserData(int userId);

    String getUserName(int userId);

    User getUserByUserName(String userName);
}
