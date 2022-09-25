package com.aliceblog.demo.POJO;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@ToString

public class Article {
    private int id;
    private String title;
    private String content;
    private String category;
    private String taps;
    private String summary;
    private Date time;

}
