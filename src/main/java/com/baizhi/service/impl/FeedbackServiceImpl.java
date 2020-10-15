package com.baizhi.service.impl;

import com.baizhi.dao.FeedbackMapper;
import com.baizhi.entity.Feedback;
import com.baizhi.service.FeedbackService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaozhi
 * @since 2020-09-24
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {

    @Autowired
    private FeedbackMapper  feedbackMapper;


    @Override
    public List<Feedback> queryAllFeedback(Integer page, Integer rows) {
        Integer  begin=rows*(page-1);
        List<Feedback> list=feedbackMapper.selectAllFeedback(begin,rows);
        return list;
    }

    @Override
    public Integer feedbackCount() {
        QueryWrapper<Feedback> queryWrapper=new QueryWrapper<>();
        Integer integer=feedbackMapper.selectCount(queryWrapper);
        return  integer;
    }

    @Override
    public void updateFeedback(Feedback feedback) {
        feedbackMapper.updateById(feedback);
    }
}
