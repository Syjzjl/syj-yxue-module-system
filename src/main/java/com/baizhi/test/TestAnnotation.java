package com.baizhi.test;

import com.baizhi.log.anno.YxueLog;

import java.lang.reflect.Method;

public class TestAnnotation {

    public static void main(String[] args) throws Exception {
        Class c = Class.forName("com.baizhi.entity.Admin");

        // 反射获取方法对象
        Method method = c.getMethod("ma", String.class);
        // 获取方法上指定类型的注解
        YxueLog annotation = method.getAnnotation(YxueLog.class);

        // 获取注解对象中的属性值
        String value = annotation.value();
        System.out.println(value);

        System.out.println(annotation.tableName());
    }
}
