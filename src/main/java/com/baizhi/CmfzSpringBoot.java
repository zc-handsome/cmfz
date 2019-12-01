package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baizhi.dao")
public class CmfzSpringBoot {
    public static void main(String[] args) {
        SpringApplication.run(CmfzSpringBoot.class, args);
        //add后idea下次会自动将代码提交到暂存区，只有第一次提交时
        //会是红色
        System.out.println();
        System.out.println("pull");
    }
}
