package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 作者：syj
 * 类的创建时间  2020/9/23 11:35
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.baizhi.dao","com.baizhi.log.dao"})
@ServletComponentScan
public class YxueApp {
    public static void main(String[] args) {
        SpringApplication.run(YxueApp.class, args);
    }
}
