package com.baizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
@TableName("yx_group")
public class Group implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    private String name; //分组名称

    private String userId; //用户id

    private Integer videoNum; //视频数量

    @DateTimeFormat(pattern = "yy-mm-dd")
    private Date createAt; //创建时间


}
