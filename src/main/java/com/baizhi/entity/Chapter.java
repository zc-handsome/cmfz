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
public class Chapter {
    private String id;
    private String title;
    private double size;
    private String duration;//时长
    private Date uploadDate;
    private String mp3Name;
    private String albumId;
}
