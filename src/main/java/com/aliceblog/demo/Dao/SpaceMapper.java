package com.aliceblog.demo.Dao;

import com.aliceblog.demo.POJO.Article;
import com.aliceblog.demo.POJO.SpaceArticle;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface SpaceMapper {
    @Select("select id from spacearticle order by id DESC limit 1 ")
    public Integer acquireLastSpaceNum();

    @Select("select * from spacearticle where id >= (1+5*(#{page}-1)) and id < (1+5*(#{page}-1))+5 order by time DESC")
    public List<SpaceArticle> selectArticleByPage(@Param("page")int page);

    @Select("select * from spacearticle ")
    public List<SpaceArticle> selectAllPages();

    @Insert("insert into spacearticle (title,content)values (#{title},#{content})")
    public void submitSpace(@Param("title")String title,@Param("content")String content);

    @Select("select * from spacearticle where id >= #{last} -(5*(#{page}-1))-4 and id <= #{last} -(5*(#{page}-1)) order by id desc ")
    public List<SpaceArticle> selectArticleByPage(@Param("page")int page,@Param("last")int last);
}
