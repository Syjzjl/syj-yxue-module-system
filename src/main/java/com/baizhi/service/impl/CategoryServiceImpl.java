package com.baizhi.service.impl;

import com.baizhi.dao.CategoryMapper;
import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建者：syj
 * 类的作用：
 * 创建时间：2020/9/24
 */
@Service
public class CategoryServiceImpl  extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateById(category);
    }

    @Override
    public List<Category> queryTwo(String id) {
        QueryWrapper<Category> queryWrapper=new QueryWrapper<>();
        //模糊查询
        queryWrapper.eq("p_id",id);
        //查询所有
        List<Category> category=categoryMapper.selectList(queryWrapper);
        System.out.println("成功"+category);
        return  category;
    }

}
