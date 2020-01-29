package mapper;

import pojo.User;
import pojo.UserData;
import pojo.UserDataForDAO;

import java.util.Map;

public interface UserMapper {
    void register(User user);

    void addNewUserData(int userId);

    String getPwd(String emailAddress);

    int checkRegister(String emailAddress);

    int getUserId(String emailAddress);

    UserDataForDAO getUserData(int userId);
}
