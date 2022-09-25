package com.aliceblog.demo.Controller;

import cn.hutool.json.JSONUtil;
import com.aliceblog.demo.Dao.BlogMapper;
import com.aliceblog.demo.POJO.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/blog")
@Slf4j
public class BlogDisplayController {

    @Autowired
    BlogMapper blogMapper;


    @RequestMapping("/pageDivide")
    @ResponseBody
    public String pageDivide(@RequestParam("page")int page){

       int last =blogMapper.acquireLastBlogNum();
       log.info("pageDivide");
       return JSONUtil.toJsonStr(blogMapper.selectArticleByPage(page,last));
    }
    @RequestMapping("/sendAll")
    @ResponseBody
    public String sendAll(){
        return JSONUtil.toJsonStr(blogMapper.selectAllPages());
    }

    @RequestMapping("/queryBlog")
    public String queryBlog(@RequestParam("id")Integer id, Model model){

        String blog=JSONUtil.toJsonStr(blogMapper.selectBlogById(id));
//        System.out.println(blog);
        model.addAttribute("blog",blog);


        return "blog";
    }

    @RequestMapping("queryCertainBlogByTitle")
    @ResponseBody
    public String queryCertain(@RequestParam("title")String title){
        List<Article> articles = blogMapper.selectBlogByTitle(title);
        return JSONUtil.toJsonStr(articles);
    }


    @RequestMapping("/allTags")
    @ResponseBody
    public String allTags(){
        return JSONUtil.toJsonStr(blogMapper.selectAllTags());
    }

    @RequestMapping("/queryBlogByTag")
    public String query(@RequestParam("tag")String tag,Model model){

        model.addAttribute("blogs",blogMapper.selectBlogByTag(tag));

        return "queryTags";

    }
}
