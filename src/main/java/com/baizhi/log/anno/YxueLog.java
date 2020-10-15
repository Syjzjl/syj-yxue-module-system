package com.baizhi.log.anno;

        import java.lang.annotation.*;

/**
 * 自定义注解：
 *  1. 元注解 ： 描述注解的注解 是java原生提供的
 *      @Target 指定当前注解可以使用在哪些位置 :
 *              ElementType.METHOD  用在方法上的意思
 *              ElementType.TYPE    用在类上的意思
 *              ElementType.FIELD   用在成员变量上的意思
 *      @Retention  用于指定注解的有效期
 *          RetentionPolicy.RUNTIME   代表程序运行时有效
 *      @Documented 指定注解时候出现在javadoc文档中
 *
 */
@Documented
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface YxueLog {
    /**
     * 对于value的属性名，在使用的时候可以省略不写，一个注解类中只能有一个为value的属性名
     * @return
     */
    String value() default "";
    String name() default "";

    String tableName() default "";
}

































