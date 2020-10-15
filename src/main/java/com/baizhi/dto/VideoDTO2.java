package com.baizhi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 创建者：syj
 * 类的作用：
 * 创建时间：2020/10/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDTO2 {
    private String id;//
    private String videoTitle;// 视频标题
    private String cover;// 视频图片
    private String path;// 视频
    private Date uploadTime;// 上传时间
    private String description; // 简介
    private String likeCount; // 点赞数
    private String cateName; //类别名
    private String categoryId;// 所属类别id
    private String userId; //所属用户id
    private String userName; //用户名
}
