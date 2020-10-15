package com.baizhi.service;

import com.baizhi.entity.Feedback;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaozhi
 * @since 2020-09-24
 */
public interface FeedbackService extends IService<Feedback> {

    List<Feedback> queryAllFeedback(Integer page, Integer rows);

    Integer  feedbackCount();

    void updateFeedback(Feedback feedback);
}
