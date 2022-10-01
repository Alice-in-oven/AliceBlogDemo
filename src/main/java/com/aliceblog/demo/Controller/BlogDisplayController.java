package com.aliceblog.demo.Controller;

import cn.hutool.json.JSONUtil;
import com.aliceblog.demo.Dao.BlogMapper;
import com.aliceblog.demo.POJO.Article;
import com.aliceblog.demo.Service.BlogCrudService;
import com.aliceblog.demo.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Component
@RestController
@RequestMapping("/blog")
public class BlogDisplayController {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    BlogCrudService blogCrudService;

    @ApiOperation(value = "分页接口")//对某个接口进行描述
    @GetMapping("pageDivide")
    public R pageDivide(@ApiParam(name = "page", defaultValue = "1",required = true) @RequestParam("page")int page){

       int last =blogMapper.acquireLastBlogNum();

        List<Article> articles = blogMapper.selectArticleByPage(page, last);
        if(articles.size()==0)return R.error().message("Fuck you");
        return R.ok().data("pageDivide",JSONUtil.toJsonStr(articles));
    }
    @ApiOperation(value = "前端获取总页数来进行分页")
    @GetMapping("/sendAll")
    public R sendAll(){
        return R.ok().data("afa",JSONUtil.toJsonStr(blogMapper.selectAllPages()));
    }
    @ApiOperation(value = "通过id获取博客")
    @GetMapping("/queryBlog")
    @Cacheable(value = {"article"}, key ="#id")//使用#号可以获取注入后的方法形参
    public R queryBlog(@RequestParam("id")Integer id, Model model){


        String blog=JSONUtil.toJsonStr(blogMapper.selectBlogById(id));

        return R.ok().data("article",blog);
    }
    @ApiOperation(value = "通过标题获取博客")
    @GetMapping("queryCertainBlogByTitle")
    public R queryCertain(@RequestParam("title")String title){
        List<Article> articles = blogMapper.selectBlogByTitle(title);
        return R.ok().data(title,JSONUtil.toJsonStr(articles));
    }

//     @GetMapping("/updateBlog")
//     public R updateBlog(@RequestParam("id")int id,@RequestParam("content")String content,@RequestParam("title") String title){
//        if(blogMapper.updateBlogById(id,content,title))
//          return   R.ok();
//        return R.error();
//
//     }

    @GetMapping("/allTags")
    public String allTags(){
        return JSONUtil.toJsonStr(blogMapper.selectAllTags());
    }

    @GetMapping("/queryBlogByTag")
    public R query(@RequestParam("tag")String tag){
        return R.ok().data("tags",JSONUtil.toJsonStr(blogMapper.selectBlogByTag(tag)));
    }


}
