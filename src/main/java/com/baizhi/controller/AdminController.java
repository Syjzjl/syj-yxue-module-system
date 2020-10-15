package com.baizhi.controller;

import com.baizhi.common.Result;
import com.baizhi.config.SendSmsUtil;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 创建者：syj
 * 类的作用：
 * 创建时间：2020/9/23
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RedisTemplate  redisTemplate;

    @RequestMapping("adminLogin")
    public  String adminLogin(Admin admin, String phone,String code,HttpSession session){
        System.out.println(phone);
        //验证验证码是否正确
        ValueOperations opsForCluster = redisTemplate.opsForValue();
//        if (!code.equals(opsForCluster.get("phone-code"))) throw new  RuntimeException("验证码错误") ;

        //登录验证
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", admin.getUsername());
        Admin admin1 = adminService.getOne(queryWrapper);
        session.setAttribute("username", admin.getUsername());
        System.out.println(admin1);
        return  "forward:/main.jsp";
    }
    //短信验证
    @RequestMapping("sendPhoneCode")
    public Result<?> sendPhoneCode(String phone){
        //获取验证码随机数并保存
        Random r = new Random();
        String  code = ""+r.nextInt(10)+r.nextInt(10)+r.nextInt(10)+r.nextInt(10);
        ValueOperations  opsForCluster=redisTemplate.opsForValue();
        //opsForCluster.set();
        opsForCluster.set( "phone-code", code, 5, TimeUnit.MINUTES);

        //发送验证码到用户手机
        String  responseData=SendSmsUtil.sendPhoneCode(phone,code);
        return  Result.ok(responseData);
    }
    //退出
    @RequestMapping("exit")
    public  String exit( HttpSession session){

        session.removeAttribute("username");
        return  "redirect:/login.jsp";
    }

//    @RequestMapping("exportUtil")
//    @ResponseBody
//    public void exportUtil() throws Exception {
//        System.out.println("====请求导出报表===");
//        // 获取数据集合
//        List<Admin> admins = getAdmins();
//        EasyPoiUtil.exportExcel(admins,"管理员信息","管理员", Admin.class,"管理员.xls",response);
//    }
//
//    @RequestMapping("import")
//    @ResponseBody
//    public void importUtil(MultipartFile multipartFile) throws Exception {
//        System.out.println("====导入报表===");
//        List<Admin> admins = EasyPoiUtil.importExcel(multipartFile, 1, 1, Admin.class);
//        for (Admin admin : admins) {
//            System.out.println(admin);
//        }
//    }
//    private List<Admin> getAdmins() {
//        List<Admin> admins = new ArrayList<>();
//        admins.add(new Admin());
//        return admins;
//    }
}
