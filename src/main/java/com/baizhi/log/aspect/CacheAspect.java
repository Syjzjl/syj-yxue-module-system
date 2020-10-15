//package com.baizhi.log.aspect;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import javax.annotation.Resource;
//import java.util.Set;
//
//@Aspect
//@Configuration
//public class CacheAspect {
//
//    @Resource
//    RedisTemplate redisTemplate;
//
//    @Resource
//    StringRedisTemplate stringRedisTemplate;
//
//
//    //环绕通知  添加缓存
//    //@Around("@annotation(com.baizhi.log.anno.AddCache)")
//    //@Around("execution(* com.baizhi.service.impl.*.page(..))")
//    public Object addCache(ProceedingJoinPoint proceedingJoinPoint){
//
//        System.out.println("=====进入环绕通知======");
//
//        //String String
//        //String Hash
//        //KEY(类名)  key(方法名+实参),value（数据）
//
//
//
//        //序列化解决乱码
//        StringRedisSerializer serializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(serializer);
//        redisTemplate.setHashKeySerializer(serializer);
//
//        //设置key value
//
//        //key=类名+方法名+实参
//        StringBuilder sb = new StringBuilder();
//
//        //获取类的全限定名
//        String className = proceedingJoinPoint.getTarget().getClass().getName();
//        sb.append(className);
//
//        //方法名
//        String methodName = proceedingJoinPoint.getSignature().getName();
//        sb.append(methodName);
//
//        //获取参数
//        Object[] args = proceedingJoinPoint.getArgs();
//        for (Object arg : args) {
//            sb.append(arg);
//        }
//
//        //获取拼接好的key
//        String key = sb.toString();
//
//        //判断是否有缓存
//        Boolean aBoolean = redisTemplate.hasKey(key);
//
//        ValueOperations opsForValue = redisTemplate.opsForValue();
//
//        Object result =null;
//        //判断key是否存在
//        if(aBoolean){
//            //取出结果返回数据
//            result = opsForValue.get(key);
//        }else{
//            //放行方法
//            try {
//                result = proceedingJoinPoint.proceed();
//            } catch (Throwable throwable) {
//                throwable.printStackTrace();
//            }
//            //加入缓存
//            opsForValue.set(key,result);
//        }
//        return result;
//    }
//
//    //清除缓存
//    //@After("@annotation(com.baizhi.log.anno.DelCache)")
//    public void delCache(JoinPoint joinPoint){
//
//        System.out.println("=====清除缓存=====");
//
//        //获取类的全限定名
//        String className = joinPoint.getTarget().getClass().getName();
//        //com.baizhi.service.impl.UserServiceImpl
//
//        Set<String> keys = stringRedisTemplate.keys("*");
//        for (String key : keys) {
//            if(key.startsWith(className)){
//                redisTemplate.delete(key);
//            }
//        }
//    }
//
//
//
//}
