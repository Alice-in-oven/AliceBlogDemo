package com.aliceblog.demo.Service;

import org.springframework.stereotype.Service;

@Service
public interface BlogCrudService {
    public boolean updateBlog(int id,String content,String title);

}
