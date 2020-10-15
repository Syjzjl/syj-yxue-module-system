package com.baizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 创建者：syj
 * 类的作用：
 * 创建时间：2020/9/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "yx_category")
public class Category implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;
    private String name;//类别名称
    private String level;//分类级别
    private String pId;//父类id
}
