package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Banner {
    @ExcelIgnore
    private String id;
    @Excel(name = "标题")
    private String title;
    @Excel(name = "状态")
    private String status;
    @Excel(name = "上传时间", format = "yyyy-MM-dd")
    private Date createTime;
    @Excel(name = "图片", type = 2)
    private String url;
    @Excel(name = "描述")
    private String descript;
}
