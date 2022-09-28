package com.aliceblog.demo;

import com.aliceblog.demo.Dao.BlogMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class DemoApplicationTests {


        @Autowired private StringRedisTemplate redisTemplate;
        @Test
        public void testSelect() {
            System.out.println(redisTemplate);
        }



}
