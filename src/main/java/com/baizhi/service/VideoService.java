package com.baizhi.service;

import com.baizhi.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 创建者：syj
 * 接口的作用：
 * 创建时间：2020/9/25
 */
public interface VideoService extends IService<Video> {

    List<Video>  queryAllVideo(Integer page, Integer rows);
    List<Video>  queryByVideo(Video video, String searchString);
    Integer videoCount();

    void updateVideo(Video video);

    List<Video>  queryVideo();
    //搜索框
    List<Video>   queryAppByVideo(String content);
    //视频详细信息展示
    List<Video>   queryVideo(String videoId,String cateId,String userId);

    List<Video>   queryNoVideo(String videoId);

    List<Video>  querySearchVideo(String content);

    //List<Video> querySearchVideos(String content);
}
