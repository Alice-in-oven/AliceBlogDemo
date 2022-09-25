//package com.aliceblog.demo.Service;
//
//import com.aliceblog.demo.Dao.BlogMapper;
//import com.aliceblog.demo.POJO.Article;
//import com.aliceblog.demo.utils.RedisUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
//@Service
//public class BlogDisplayService {
//
//    List<Article> articles;
//    @Autowired
//    BlogMapper blogMapper;
//
//        @Autowired
//        private RedisUtil redisUtil;
//
//        public List<Article> queryAllBlogs() {
//
//            if(redisUtil.hasKey(key)) {
//                user = (User)redisUtil.get(key);
//                System.out.println("查询的是缓存");
//            }else{
//                 articles= blogMapper.selectAllPages();
//                System.out.println("查询的是数据库");
//                for(Article article : articles){
//                    String key = "cache:"+id;
//                    System.out.println(redisUtil.set(key,article) ? "插入成功" : "插入失败");
//                }
//
//            }
//            return articles;
//        }
//
//
//
//
//}
