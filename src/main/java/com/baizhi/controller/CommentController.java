package com.baizhi.controller;


import com.baizhi.common.Result;
import com.baizhi.common.Result2;
import com.baizhi.entity.Comment;
import com.baizhi.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService  commentService;

    @RequestMapping("showAllComment")
    public Result2<?> showAllComment(Integer page, Integer rows){
        System.out.println("111----------------------");
        Result2<List<Comment>> result = new Result2<>();
        List<Comment> list = commentService.queryOneComment(page,rows);

        Integer totals = commentService.commentOneCount(page,rows);
        Integer total = totals % rows == 0 ? totals / rows : totals / rows + 1;
        result.setPage(page);
        result.setRows(list);
        result.setTotal(total);
        result.setRecords(totals);
        return  result;
    }

    @RequestMapping("showByIdComment")
    public Result2<?>   showByIdComment(Integer page,Integer rows,String id){
        Result2<List<Comment>> result = new Result2<>();
        List<Comment> list = commentService.queryTwoComment(page,rows,id);
        Integer totals = commentService.commentTwoCount(page,rows,id);
        Integer total = totals % rows == 0 ? totals / rows : totals / rows + 1;
        result.setPage(page);
        result.setRows(list);
        result.setTotal(total);
        result.setRecords(totals);
        return  result;
    }

    @ResponseBody
    @RequestMapping("edit")
    public Result<?> edit(Comment comment, String oper){
        System.out.println("添加评论"+comment);
        Result result = new Result();
        if(oper.equals("add")){
            if (comment.getInteractId()==null) {
                comment.setId(null);
                comment.setUserId(comment.getFuser().getUsername());
                System.out.println("视频id："+comment.getFvideo().getVideoUrl());
                comment.setSourceId(comment.getFvideo().getVideoUrl());
                comment.setCreateAt(new Date());
                comment.setInteractId(null);
                commentService.save(comment);
            }else if (comment.getInteractId()!=null) {
                comment.setId(null);
                comment.setUserId(comment.getFuser().getUsername());
                comment.setCreateAt(new Date());
                commentService.save(comment);
            }

        }if(oper.equals("del")){

            commentService.removeComment(comment.getId());

        }if (oper.equals("edit")){
            commentService.updateComment(comment);
        }
        return result;
    }
}

