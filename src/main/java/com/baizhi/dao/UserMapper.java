package com.baizhi.dao;

import com.baizhi.entity.City;
import com.baizhi.entity.Month;
import com.baizhi.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {
    List<City> queryCity(String sex);
    List<Month> queryMonth(String sex);
    List<String> monthCount();
}
