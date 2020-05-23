package com.how2java.springboot;

//import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

@SpringBootApplication
class SpringbootApplicationTests {

    @Test
    void contextLoads() {
        StringBuffer s = new StringBuffer("梁康");
        s.append("爱");
        System.out.println(s);

    }

}
