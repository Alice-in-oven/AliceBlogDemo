package com.aliceblog.demo.Dao;

import com.aliceblog.demo.POJO.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Mapper

public interface BlogMapper {
    @Select("select * from article where id >= #{last} -(5*(#{page}-1))-4 and id <= #{last} -(5*(#{page}-1)) order by id desc ")
    public List<Article> selectArticleByPage(@Param("page")int page,@Param("last")int last);

    @Select("select * from article ")
    public List<Article> selectAllPages();

    @Select("select * from article where id=#{id}")
    public Article selectBlogById(@Param("id")Integer id);

    @Insert("insert into article (title,content,summary,taps)values (#{title},#{content},#{summary},#{taps})")
    public void submitBlog(@Param("title")String title,@Param("content")String content,@Param("summary")String summary,@Param("taps")String taps);

    @Select("select id from article order by id DESC limit 1 ")
    public Integer acquireLastBlogNum();

    @Select("select * from article where title like '%${title}%' ")
    public List<Article> selectBlogByTitle(@Param("title")String title);

    @Select("select DISTINCT taps from article")
    public List<String>  selectAllTags ();

    @Select("select * from article where taps = #{tap}")
    public List<Article> selectBlogByTag(@Param("tap")String tap);

}

