package com.baizhi.controller;

import com.baizhi.common.Result;
import com.baizhi.log.entity.Log;
import com.baizhi.log.service.LogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 创建者：syj
 * 类的作用：
 * 创建时间：2020/9/28
 */
@Slf4j
@RestController
@Controller
@RequestMapping("log")
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping("showLog")
    public Result<?> showLog(Integer page, Integer rows){

        IPage pageInfo = new Page(page, rows);
        QueryWrapper<Log> queryWrapper = new QueryWrapper<>();
        IPage pageList =logService.page(pageInfo, queryWrapper);
        List records = pageList.getRecords();
        System.out.println(records);
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
    public Result<?> edit(Log log,String oper){
        Result result = new Result();
        if(oper.equals("del")){
            logService.removeById(log.getId());
        }if (oper.equals("edit")){
            //log.s(null);
            //logService.updateUser(user);
        }
        return result;
    }
}
