//package com.how2java.springboot.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//@EnableCaching
//public class RedisConfiguration extends CachingConfigurerSupport {
//
//    /*
//     * 采用RedisCacheManager作为缓存管理器
//     * @param connectionFactory
//     */
//
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//        RedisCacheManager redisCacheManager = RedisCacheManager.create(connectionFactory);
//        return redisCacheManager;
//    }
//
//    // 用于存取值为对象时
//    @Bean("RedisTemplate")
//    RedisTemplate<String, Object> RedisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//
//        // RedisTemplate key/value 默认的序列化策略
//        JdkSerializationRedisSerializer redisSerializer = new JdkSerializationRedisSerializer();
//
//        // StringRedisTemplate key/value 默认的序列化策略
//        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
//
//        template.setConnectionFactory(factory);   // 配置redis连接
//        template.setKeySerializer(stringSerializer);  // 配置键序列化器
//        template.setValueSerializer(redisSerializer);  // 配置值序列化器
//
//        return template;
//    }
//
//}