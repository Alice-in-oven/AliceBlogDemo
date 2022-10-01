package com.aliceblog.demo.Controller;

import cn.hutool.json.JSONUtil;
import com.aliceblog.demo.Dao.BlogMapper;
import com.aliceblog.demo.POJO.Article;
import com.aliceblog.demo.Service.BlogCrudService;
import com.aliceblog.demo.common.R;

import com.aliceblog.demo.utils.RedisUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@Controller
@ResponseBody
@RequestMapping("/blog")
public class BlogDisplayController {

    @GetMapping("/fuck")
    public String fuck(){
        return "FuckYou!";
    }

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    BlogCrudService blogCrudService;

    @Autowired
    RedisUtil redisUtil;


    @GetMapping("pageDivide")
    public R pageDivide( @RequestParam("page")int page){

       int last =blogMapper.acquireLastBlogNum();

        List<Article> articles = blogMapper.selectArticleByPage(page, last);
        if(articles.size()==0)return R.error().message("Fuck you");
        return R.ok().data("pageDivide",JSONUtil.toJsonStr(articles));
    }

    @GetMapping("/sendAll")
    public R sendAll(){
        return R.ok().data("afa",JSONUtil.toJsonStr(blogMapper.selectAllPages()));
    }

    @GetMapping("/queryBlog")

    public R queryBlog(@RequestParam("id")Integer id, Model model){
        Article blog;
        if(redisUtil.hasKey(id.toString())) {
            blog = (Article)redisUtil.get(id.toString());
            System.out.println("用的是缓存哦");
        } else {
            blog = blogMapper.selectBlogById(id);
            redisUtil.set(id.toString(),blog);
        }

        return R.ok().data("article",JSONUtil.toJsonStr(blog));
    }

    @GetMapping("queryCertainBlogByTitle")
    public R queryCertain(@RequestParam("title")String title){
        List<Article> articles = blogMapper.selectBlogByTitle(title);
        return R.ok().data(title,JSONUtil.toJsonStr(articles));
    }

     @GetMapping("/updateBlog")
     @RequiresRoles("vip")
     public R updateBlog(@RequestParam("id")int id,@RequestParam("content")String content,@RequestParam("title") String title){
        if(blogMapper.updateBlogById(id,content,title))
          return   R.ok();
        return R.error();

     }

    @GetMapping("/allTags")
    public String allTags(){
        return JSONUtil.toJsonStr(blogMapper.selectAllTags());
    }

    @GetMapping("/queryBlogByTag")
    public R query(@RequestParam("tag")String tag){
        return R.ok().data("tags",JSONUtil.toJsonStr(blogMapper.selectBlogByTag(tag)));
    }


}
