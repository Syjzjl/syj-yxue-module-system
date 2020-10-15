package com.baizhi.entity;

import com.baizhi.log.anno.YxueLog;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author syj
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("yx_admin")
public class Admin implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    private String username;

    private String password;

    @YxueLog(value = "用户信息删除操作",tableName = "yx_admin")
    public void ma(String str) {
        System.out.println("ma");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
