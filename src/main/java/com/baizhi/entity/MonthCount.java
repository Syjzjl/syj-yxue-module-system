package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 作者：syj
 * 类的创建时间  2020/10/13 20:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthCount {
    private String sex;
    private List<Integer> counts;
}

