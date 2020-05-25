package org.lk.daydayup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.lk.daydayup.mapper")
public class DaydayupApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaydayupApplication.class, args);
    }

}
