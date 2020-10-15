package com.baizhi.log.aspect;

import com.baizhi.log.anno.YxueLog;
import com.baizhi.log.entity.Log;
import com.baizhi.log.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

@Configuration
@Aspect
public class LogAspect {

    @Autowired
    private HttpSession session;
    private LogService logService;

    @Around("@annotation(com.baizhi.log.anno.YxueLog)")
    public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("=====执行日志记录操作====");
        /**
         * 获取操作的用户名
         */
        Object username = session.getAttribute("username");
        System.out.println("username:"+username);
        // 获取操作时间
        Date currentDate = new Date();
        System.out.println("date:"+currentDate);
        // 操作的表名和操作的业务类型，通过对方法上的注解属性值解析得到
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        // 获取方法对象
        Method method = methodSignature.getMethod();
        // 获取方法上的注解然后解析
        YxueLog anno = method.getAnnotation(YxueLog.class);
        String tableName = anno.tableName();
        String operationMethod = anno.value();
        System.out.println("tableName:"+tableName);
        System.out.println("operationMethod:"+operationMethod);
        // 获取方法完整签名
        System.out.println(methodSignature.toString());

        System.out.println("=====执行日志记录操作====");

        // 执行原始方法,并传参数
        Object[] args = joinPoint.getArgs();// 获取传入的参数
        Object returnValue = joinPoint.proceed(args);

        Log log=new Log();
        log.setUsername((String) username);
        log.setOperationAt(new Date());
        log.setTableName(tableName);
        log.setOperationMethod(operationMethod);
        log.setMethodName(methodSignature.toString());
        log.setDateId(null);//UUID.randomUUID().toString()
        log.setDateInfo(null);
        logService.save(log);
        return returnValue;
    }
}
























