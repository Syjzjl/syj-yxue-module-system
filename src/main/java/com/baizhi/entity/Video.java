package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author SYJ
 * @since 2020-09-24
 */
@Document(indexName="yingx",type = "video")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("yx_video")
@NoArgsConstructor
@AllArgsConstructor
public class Video implements Serializable {



    @TableId(type = IdType.UUID)
    @Id
    private String id;

    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String title; //标题

    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String intro; //描述

    @Field(type = FieldType.Keyword)
    private String coverUrl;

    @Field(type = FieldType.Keyword)
    private String videoUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Field(type = FieldType.Date)
    private Date createAt;

    @Field(type = FieldType.Keyword)
    private String userId;

    @Field(type = FieldType.Keyword)
    private String cId;

    @Field(type = FieldType.Keyword)
    private String grpId;

    @TableField(exist = false)
    private User fuser;  //用户关系属性

    @TableField(exist = false)
    private  Category fcate; //类别关系属性

    @TableField(exist = false)
    private  Group fgroup; //分组关系属性

    @TableField(exist = false)
    private  String  likeNum;//点赞数

    @TableField(exist = false)
    private  Play  fplay; // 播放关系属性

}
