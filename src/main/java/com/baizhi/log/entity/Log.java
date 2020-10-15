package com.baizhi.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author syj
 * @since 2020-09-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("yx_log")
public class Log implements Serializable {


    @TableId(type = IdType.UUID)
    private String id; //日志编号

    private String username; //操作用户名

    private Date operationAt; //操作时间

    private String tableName; //操作表名

    private String operationMethod; //操作的业务类型

    private String methodName; //操作的方法签名


    private String dateId; //操作的数据id

    private String dateInfo; //数据恢复


}
