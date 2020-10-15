package com.baizhi.controller;

import com.baizhi.common.Result;
import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建者：syj
 * 类的作用：
 * 创建时间：2020/9/24
 */
@Slf4j
@RestController
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("showCategory")
    public Result<?> showCategory(Integer page, Integer rows, Category category){
        IPage pageInfo = new Page(page, rows);
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        IPage pageList = categoryService.page(pageInfo, queryWrapper.eq("level","一"));

        return Result.ok(
                pageList.getRecords(),
                pageList.getCurrent(),
                pageList.getPages(),
                pageList.getTotal()
        );
    }
    @RequestMapping("showByIdCategory")
    public Result<?>   showByIdCategory(Integer page,Integer rows,String id){
        //System.out.println(id);
        IPage<Category> pageInfo = new Page(page, rows);
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        IPage pageList = categoryService.page(pageInfo, queryWrapper.eq("p_id",id));
        return Result.ok(
                pageList.getRecords(),
                pageList.getCurrent(),
                pageList.getPages(),
                pageList.getTotal()
        );
    }
    //添加、修改、删除
    @ResponseBody
    @RequestMapping("edit")
    public Result<?> edit(Category category,String oper){
        Result result = new Result();
        if(oper.equals("add")){
            if (category.getPId()==null) {
                category.setId(null);
                category.setLevel("一");
                category.setPId(null);
                categoryService.save(category);
            }else if (category.getPId()!=null) {
                category.setId(null);
                category.setLevel("二");
                categoryService.save(category);
            }
            System.out.println("category = " + category);
        }if(oper.equals("del")){

            if (category.getPId()!=null) {
                categoryService.removeById(category.getId());
            } else if (categoryService.queryTwo(category.getId())==null){
                categoryService.removeById(category.getId());
            }

        }if (oper.equals("edit")){
                categoryService.updateCategory(category);
        }
        return result;
    }
}
