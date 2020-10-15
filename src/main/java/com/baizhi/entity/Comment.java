package com.baizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
@TableName("yx_comment")
public class Comment implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    private String userId; //用户id

    private String sourceId; //资源id

    private String content; //评论内容

    @DateTimeFormat(pattern = "yy-mm-dd")
    private Date createAt; //评论时间

    private String interactId; //互动评论id

    @TableField(exist = false)
    private User fuser;  //用户关系属性

    @TableField(exist = false)
    private Video fvideo;  //视频关系属性


}
