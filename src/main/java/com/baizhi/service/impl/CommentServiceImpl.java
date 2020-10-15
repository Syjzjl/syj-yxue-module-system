package com.baizhi.service.impl;

import com.baizhi.dao.CommentMapper;
import com.baizhi.entity.Comment;
import com.baizhi.service.CommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public List<Comment> queryOneComment(Integer page, Integer rows) {
        Integer begin=rows*(page-1);
        List<Comment>  list=commentMapper.selectComment(begin,rows);
        return  list;
    }

    @Override

    public List<Comment> queryTwoComment(Integer page, Integer rows,String id) {
        Integer begin=rows*(page-1);
        List<Comment>  list=commentMapper.selectTwoComment(begin,rows,id);
        return  list;
    }

    //一级评论总数
    @Override
    public Integer commentOneCount(Integer page, Integer rows) {
        Integer begin=rows*(page-1);
        List<Comment>  list=commentMapper.selectComment(begin,rows);
        Integer integer=0;
        for (int i=1;i<=list.size();i++){
            integer+=1;
        }
        return  integer;
    }
    //二级评论总数
    @Override
    public Integer commentTwoCount(Integer page, Integer rows, String id) {
        Integer begin=rows*(page-1);
        List<Comment>  list=commentMapper.selectTwoComment(begin,rows,id);
        Integer integer=0;
        for (int i=1;i<=list.size();i++){
            integer+=1;
        }
        return  integer;
    }

    @Override
    public void removeComment(String id) {
        Comment  comment=commentMapper.selectById(id);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("interact_id",comment.getId());
        List<Comment>  list=commentMapper.selectList(queryWrapper);
        if (comment.getInteractId()==null&&list.size()==0){
            commentMapper.deleteById(comment.getId());
        }else if (comment.getInteractId()!=null){
            commentMapper.deleteById(comment.getId());
        }
    }

    @Override
    public void updateComment(Comment comment) {
        commentMapper.updateById(comment);
    }

}
