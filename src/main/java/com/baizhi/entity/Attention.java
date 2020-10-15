package com.baizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
@TableName("yx_attention")
public class Attention implements Serializable {

    @TableId(type = IdType.UUID)
    private String id; //关注id

    private String fromUserId; //粉丝

    private String toUserId; //博主


}
