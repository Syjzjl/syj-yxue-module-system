package com.baizhi.service.impl;

import com.baizhi.dao.UserMapper;
import com.baizhi.entity.City;
import com.baizhi.entity.Month;
import com.baizhi.entity.User;
import com.baizhi.log.anno.YxueLog;
import com.baizhi.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * 创建者：syj
 * 类的作用：
 * 创建时间：2020/9/23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User queryById(String id) {
         User user=userMapper.selectById(id);
        return user;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    @YxueLog(value = "用户删除",tableName = "yx_user")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @YxueLog
    @Override
    public boolean save(User entity) {
        return super.save(entity);
    }

    @Override
    public List<City> queryCity(String sex){
        List<City> list=userMapper.queryCity(sex);
        return list;
    }
    @Override
    public List<Month> queryMonth(String sex) {
        return userMapper.queryMonth(sex);
    }

    @Override
    public List<String> monthCount(){
        return userMapper.monthCount();
    }
}
