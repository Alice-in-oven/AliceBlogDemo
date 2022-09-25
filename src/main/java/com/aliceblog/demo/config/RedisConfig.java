//package com.aliceblog.demo.config;
//
//import com.aliceblog.demo.POJO.Article;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//
//@Configuration
//@EnableAutoConfiguration
//public class RedisConfig {
//
//        @Bean
//        public RedisTemplate<Object, Article> redisTemplate(RedisConnectionFactory redisConnectionFactory){
//            RedisTemplate<Object, Article> template = new RedisTemplate<>();
//            template.setConnectionFactory(redisConnectionFactory);
//
//            Jackson2JsonRedisSerializer<Article> ser = new Jackson2JsonRedisSerializer<>(Article.class);
//            template.setDefaultSerializer(ser);
//            return template;
//        }
//    }
//
