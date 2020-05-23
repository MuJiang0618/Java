package com.how2java.springboot;

import com.how2java.springboot.pojo.RedisUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)   // main中的springboot启动类, 不要弄错名字了
public class TestRedis {
    @Autowired StringRedisTemplate stringRedisTemplate;
    @Autowired @Qualifier("RedisTemplate") RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() {
        //保存对象
        RedisUser redisUserUser = new RedisUser();
        redisTemplate.opsForValue().set("lk", redisUserUser);
        redisTemplate.expire("lk", 10, TimeUnit.SECONDS);    //对单个key设置失效时间
        RedisUser redisUser = (RedisUser) redisTemplate.opsForValue().get("lk");
    }

}
