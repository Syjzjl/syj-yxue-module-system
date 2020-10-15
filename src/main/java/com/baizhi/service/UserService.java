package com.baizhi.service;

import com.baizhi.entity.City;
import com.baizhi.entity.Month;
import com.baizhi.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.List;

/**
 * 创建者：syj
 * 接口的作用：
 * 创建时间：2020/9/23
 */
public interface UserService extends IService<User> {

    User queryById(String id);

    void updateUser(User user);

    @Override
    boolean save(User entity);

    @Override
    boolean removeById(Serializable id);

    List<City> queryCity(String sex);
    List<Month> queryMonth(String sex);
    List<String> monthCount();
}
