package com.baizhi.dao;

import com.baizhi.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper extends BaseMapper<Video> {


    List<Video>  selectAllVideo(@Param("begin") Integer begin, @Param("rows") Integer rows);

    List<Video>   selectVideo();

    List<Video>   selectAppVideo(@Param("videoId") String videoId,@Param("cateId") String cateId,@Param("userId") String userId);

    List<Video>   selectNoVideo(String videoId);

    List<Video>   selectAppByVideo(String content);

    List<Video>   queryAll();

}
