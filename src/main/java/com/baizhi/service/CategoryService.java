package com.baizhi.service;

import com.baizhi.entity.Category;
import com.baizhi.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 创建者：xw
 * 接口的作用：
 * 创建时间：2020/9/24
 */
public interface CategoryService extends IService<Category> {

    void updateCategory(Category category);

    List<Category> queryTwo(String id);
}
