package com.aliceblog.demo.POJO;


import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@ToString
public class SpaceArticle {
    private int id;
    private String title;
    private String content;
    private Date time;
}
