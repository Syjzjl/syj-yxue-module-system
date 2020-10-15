package com.baizhi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("yx_img")
public class Img implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    private String imgUrl;

    private String intro;

    private Date publishTime;

    private String userId;


}
