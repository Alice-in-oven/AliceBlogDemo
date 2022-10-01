package com.aliceblog.demo.Service.impl;

import com.aliceblog.demo.Dao.BlogMapper;
import com.aliceblog.demo.POJO.Article;
import com.aliceblog.demo.Service.BlogCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogCrudService {
    @Autowired
    BlogMapper blogMapper;
    @Override
    public boolean updateBlog(int id,String content,String title) {
        Article article = blogMapper.selectBlogById(id);
        if(article==null)return false;
        blogMapper.updateBlogById(id,content,title);
        return true;


    }
}
