package com.how2java.springboot.pojo;

import java.io.Serializable;

//@Data
public class RedisUser implements Serializable {

    public String userName = "lh";

    public Integer age = 20;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }
}