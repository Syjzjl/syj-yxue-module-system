package com.baizhi.controller;

import com.alibaba.fastjson.JSON;
import com.baizhi.common.Result;
import com.baizhi.entity.*;
import com.baizhi.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.goeasy.GoEasy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 创建者：syj
 * 类的作用：
 * 创建时间：2020/9/23
 */
@Slf4j
@RestController
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService  userService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @RequestMapping("userLogin")
    public Result<?> userLogin(){

        return null;
    }
    @RequestMapping("showUser")
    public  Result<?>  showUser(Integer page,Integer rows,User user){

        IPage pageInfo = new Page(page, rows);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        IPage pageList =userService.page(pageInfo, queryWrapper);

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
    public Result<?> edit(User user,String oper){
        Result result = new Result();
        if(oper.equals("add")){
            user.setId(null);
            user.setStatus("1");
            user.setRegTime(new Date());
            userService.save(user);
            result.setMessage(user.getId());
            System.out.println("user = " + user);
        }if(oper.equals("del")){
            userService.removeById(user.getId());
        }if (oper.equals("edit")){
            user.setHeadShow(null);
            userService.updateUser(user);
        }
        return result;
    }
    //修改激活状态
    @RequestMapping("updateStatus")
    public void updateStatus(String id){
        User user = userService.queryById(id);
        if(user.getStatus().equals("1")){
            user.setStatus("2");
        }else{
            user.setStatus("1");
        }
        userService.updateUser(user);
    }
    //进行文件上传
    @RequestMapping("headUpload")
    public void headUpload(String id, MultipartFile headShow) throws Exception{
        System.out.println("哈哈哈"+id);
        String filename=headShow.getOriginalFilename();
        System.out.println(filename);
        //1.获取绝对路径
        String path=request.getServletContext().getRealPath("/img");
        //2.文件上传
        headShow.transferTo(new File(path+"/"+filename));
        User user=new User();
        user.setId(id);
        user.setHeadShow(request.getContextPath()+""+filename);
        userService.updateById(user);
    }



    @RequestMapping("city")
    public StringBuilder city(){
        String city[] = {"陕西","甘肃","青海",
                "宁夏","新疆",
                "北京","天津","上海","重庆",
                "河北","山西","辽宁","吉林",
                "黑龙江","江苏","浙江","安徽",
                "福建","江西","山东","河南","湖北",
                "湖南","广东","海南","四川","贵州","云南",
                "台湾","内蒙古","广西","西藏",
                "香港","澳门"};

        StringBuilder builder = new StringBuilder();

        builder.append("<select>");
        for (String s : city) {
            builder.append("<option value='").append(s).append("'>").append(s).append("</option>");
        }
        builder.append("</select>");
        return builder;
    }


    @RequestMapping("queryUser")
    public Months queryCount(){

        List<String> monthCounts = userService.monthCount();
        System.out.println("--------成功--------");

        Months months = new Months();
        months.setMonths(monthCounts);
        System.out.println(months);

        List<Month> boys = userService.queryMonth("男");
        List<Month> girls = userService.queryMonth("女");

        MonthCount monthCount1 = new MonthCount();
        MonthCount monthCount2 = new MonthCount();
        monthCount1.setSex("男");
        monthCount2.setSex("女");
        System.out.println(monthCount1);
        System.out.println(monthCount2);

        List<Integer> integers1 = new ArrayList<>();
        List<Integer> integers2 = new ArrayList<>();

        for (int i=0;i<boys.size();i++){
            Month month = boys.get(i);
            integers1.add(month.getCount());
        }


        for (int i=0;i<girls.size();i++){
            Month month = girls.get(i);
            integers2.add(month.getCount());
        }

        monthCount1.setCounts(integers1);
        monthCount2.setCounts(integers2);

        List<MonthCount> list = new ArrayList<>();
        list.add(monthCount1);
        list.add(monthCount2);

        months.setMonthCounts(list);
        System.out.println(months);
        return months;
    }


    @RequestMapping("queryMap")
    public List<Common> queryMap() {
        List<City> boyList = userService.queryCity("男");
        List<City> girlList = userService.queryCity("女");
        Common commonboy = new Common("男", boyList);
        Common commongirl = new Common("女", girlList);
        List<Common> commons = new ArrayList<>();
        System.out.println(commonboy);
        System.out.println(commongirl);
        commons.add(commonboy);
        commons.add(commongirl);

        //将map集合转为json字符串
        String str = JSON.toJSONString(commons);
        //初始化GoEasy   参数：地区地址，appKey
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-9ae9e05cdd2a4c568224055de1132131");

        //发送数据   参数：通道名称，发送的内容
        goEasy.publish("2004Channel", str);

        return commons;
    }




//    @RequestMapping("exportUtil")
//    @ResponseBody
//    public void exportUtil() throws Exception {
//        System.out.println("====请求导出报表===");
//        // 获取数据集合
//        List<User> users = getUsers();
//        EasyPoiUtil.exportExcel(users,"用户信息","用户", User.class,"用户.xls",response);
//    }
//
//    @RequestMapping("import")
//    @ResponseBody
//    public void importUtil(MultipartFile multipartFile) throws Exception {
//        System.out.println("====导入报表===");
//        List<User> users = EasyPoiUtil.importExcel(multipartFile, 1, 1, User.class);
//        for (User user : users) {
//            System.out.println(user);
//        }
//    }
//    private List<User> getUsers() {
//        List<User> users = new ArrayList<>();
//        return users;
//    }

}
