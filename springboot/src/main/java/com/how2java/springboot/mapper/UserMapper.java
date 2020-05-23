package com.how2java.springboot.mapper;

import com.how2java.springboot.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    public void register(User user);

    public User[] listAllUser();

    public String getPwd(String userName);

    public int checkRegister(String userName);

    public int getUserId(String userName);

    public String getUserName(int userId);

    public User getUserByUserName(String userName);

    public String getNicknameById(int userId);
}
