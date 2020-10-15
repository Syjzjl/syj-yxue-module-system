package com.baizhi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 创建者：syj
 * 类的作用：
 * 创建时间：2020/10/10
 */
@Data
@AllArgsConstructor
//@NoArgsConstructor
public class VideoDTO3 {
    private String   id;//
    private String   videoTitle;// 视频标题
    private String   cover;// 视频图片
    private String   path;// 视频
    private Date     uploadTime;// 上传时间
    private String   description; // 简介
    private String     likeCount; // 点赞数
    private String   cateName; //类别名
    private String   categoryId;// 所属类别id
    private String   userId; //所属用户id
    private String   userPicImg; //用户头像
    private String   userName; //用户名
    private Integer   playCount; //播放次数
    private Boolean   isAttention; //是否关注该用户
    private List<VideoDTO3> videoList; //视频集合

    public VideoDTO3(String id, String videoTitle, String cover, String path, Date uploadTime, String description, String likeCount, String cateName, String categoryId, String userId) {
        this.id = id;
        this.videoTitle = videoTitle;
        this.cover = cover;
        this.path = path;
        this.uploadTime = uploadTime;
        this.description = description;
        this.likeCount = likeCount;
        this.cateName = cateName;
        this.categoryId = categoryId;
        this.userId = userId;
    }
}
