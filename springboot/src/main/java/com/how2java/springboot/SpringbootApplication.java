package com.how2java.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching // 开启redis
@ServletComponentScan        // 扫描servlet的filter和listener
@MapperScan(basePackages = {"com.how2java.springboot.mapper", "com.how2java.springboot.service"})   // 扫描mapper文件夹
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
