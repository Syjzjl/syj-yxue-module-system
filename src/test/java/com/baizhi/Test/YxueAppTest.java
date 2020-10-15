package com.baizhi.Test;

import com.baizhi.YxueApp;
import com.baizhi.dao.*;
import com.baizhi.entity.*;
import com.baizhi.log.dao.LogMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = YxueApp.class)
public class YxueAppTest {

    @Autowired
    private AdminMapper  adminMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private UserMapper  userMapper;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private FeedbackMapper feedbackMapper;
    @Autowired
    private LogMapper logMapper;

    //测试管理员用户
    @Test
    public void testAdmin() {
         Admin admin=adminMapper.selectById(1);
        QueryWrapper<Admin> queryWrapper=new QueryWrapper<>();
        List<Admin> list=adminMapper.selectList(queryWrapper);
        for(Admin admin1:list) {
            System.out.println(admin1);
        }
    }
    //测试普通用户
    @Test
    public void testUser() {

        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        List<User> list=userMapper.selectList(queryWrapper);
        for(User user:list) {
            System.out.println(user);
        }
    }
    //测试类别
    @Test
    public void testCategory() {
        QueryWrapper<Category> queryWrapper=new QueryWrapper<>();
        //模糊查询
        queryWrapper.eq("p_id","1");
        //查询所有
        List<Category> category=categoryMapper.selectList(queryWrapper);
        for(Category category1:category) {
            System.out.println(category1);
        }
/*
       //分页查询
        IPage<Category> page=new Page<>(2,5);

        IPage<Category> iPage=categoryDao.selectPage(page,null);
        System.out.println("信息总数："+iPage.getTotal());
        List<Category> list=iPage.getRecords();
        for (Category category:list){
            System.out.println(category);
        }*/
    }

    //测试评论
    @Test
    public void testComment() {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        //查询所有
        List<Comment> comment = commentMapper.selectList(queryWrapper);
        for (Comment comment1 : comment) {
            System.out.println(comment1);
        }
    }
//    //测试视频
//    @Test
//    public void selectAllVideo() {
//        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
//        //查询所有
//        List<Video> video = videoMapper.selectAllVideo(1,3);
//        for (Video video1 : video) {
//            System.out.println(video1);
//        }
//    }

    //测试反馈
    @Test
    public void testFeedback() {
        QueryWrapper<Feedback> queryWrapper = new QueryWrapper<>();
        //模糊查询
        //queryWrapper.eq("p_id","1");
        //查询所有
        List<Feedback> feedback = feedbackMapper.selectList(queryWrapper);
        for (Feedback feedback1 : feedback) {
            System.out.println(feedback1);
        }
    }

//    //测试日志
//    @Test
//    public void selectAll() {
//    QueryWrapper<Log> queryWrapper = new QueryWrapper<>();
//       List<Log> log = logMapper.selectAll(1,3);
//       for (Log log1 : log) {
//       System.out.println(log1);
//        }
//   }

//    @Test
//    public void queryByReleaseTime() {
//    QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
//       List<Video> videos = videoMapper.queryByReleaseTime();
//       for (Video video1 : videos) {
//       System.out.println(video1);
//        }
//   }

}
