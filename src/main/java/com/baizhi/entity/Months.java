package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * 作者：syj
 * 类的创建时间  2020/10/13 20:07
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Months {
    private List<String> months;
    private List<MonthCount> monthCounts;
}

