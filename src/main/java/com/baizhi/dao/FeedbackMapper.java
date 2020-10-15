package com.baizhi.dao;

import com.baizhi.entity.Feedback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaozhi
 * @since 2020-09-24
 */
public interface FeedbackMapper extends BaseMapper<Feedback> {

    List<Feedback> selectAllFeedback(@Param("begin") Integer begin, @Param("rows") Integer rows);

}
