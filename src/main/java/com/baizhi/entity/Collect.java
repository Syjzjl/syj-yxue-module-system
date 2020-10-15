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
@TableName("yx_collect")
public class Collect implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    private String userId; //用户编号

    private String sourceId; //资源编号

    @DateTimeFormat(pattern = "yy-mm-dd")
    private Date collectTime; //收藏时间


}
