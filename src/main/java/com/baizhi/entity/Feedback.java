package com.baizhi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaozhi
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("yx_feedback")
public class Feedback implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    private String title;

    private String content;
    @DateTimeFormat(pattern = "yy-mm-dd")
    private Date createAt;

    private String userId;

    @TableField(exist = false)
    private User fuser;  //用户关系属性



}
