package com.baizhi.controller;


import com.baizhi.common.Result;
import com.baizhi.common.Result2;
import com.baizhi.entity.Feedback;
import com.baizhi.service.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


@Slf4j
@RestController
@Controller
@RequestMapping("feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService  feedbackService;

    @RequestMapping("showFeedback")
    public Result2<?> showFeedback(Integer page, Integer rows){

        Result2<List<Feedback>> result = new Result2<>();
        List<Feedback> list = feedbackService.queryAllFeedback(page, rows);

        Integer totals = feedbackService.feedbackCount();
        Integer total = totals % rows == 0 ? totals / rows : totals / rows + 1;
        result.setPage(page);
        result.setRows(list);
        result.setTotal(total);
        result.setRecords(totals);
        return  result;
    }
    @ResponseBody
    @RequestMapping("edit")
    public Result<?> edit(Feedback feedback, String oper){
        System.out.println("添加反馈"+feedback);
        Result result = new Result();
        if(oper.equals("add")){

            feedback.setId(null);
            feedback.setUserId(feedback.getFuser().getUsername());
            feedback.setCreateAt(new Date());
            feedbackService.save(feedback);

        }if(oper.equals("del")){

            feedbackService.removeById(feedback.getId());

        }if (oper.equals("edit")){
            feedbackService.updateFeedback(feedback);
        }
        return result;
    }

}

