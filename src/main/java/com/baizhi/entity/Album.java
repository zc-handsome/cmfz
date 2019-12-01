package com.baizhi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Album {
    private String id;
    private String title;
    private String author;
    private String announcer;
    private Integer chapterNum;
    private Integer score;
    private String albumIntro;
    private String status;
    private Date issueTime;//发行时间
    private Date uploadTime;
    private String picture;
}
