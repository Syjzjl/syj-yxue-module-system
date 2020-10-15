package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 创建者：syj
 * 类的作用：
 * 创建时间：2020/9/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "yx_user")
public class User {

    @TableId(type = IdType.UUID)
    @Excel(name="id")
    private   String  id;
    @Excel(name="姓名")
    private   String  username;
    @Excel(name="手机号")
    private   String  mobile;
    @Excel(name="签名")
    private   String  sign;
    @Excel(name="头像")
    private   String  headShow;
    @Excel(name="状态")
    private   String  status;
    @DateTimeFormat(pattern = "yy-mm-dd")
    @Excel(name="注册时间")
    private   Date    regTime;
    @Excel(name="分数")
    private   Double  score;
    @Excel(name="微信")
    private   String  wechat;
}
