package com.aliceblog.demo;

import com.aliceblog.demo.Dao.BlogMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    BlogMapper blogMapper;
    @Test
    void test() {
//        System.out.println(blogMapper.acquireLastNum());
    }

}
