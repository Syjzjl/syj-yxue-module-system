
package com.baizhi.Test;

import com.baizhi.YxueApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 作者：syj
 * 类的创建时间  2020/9/27 14:37
 */
@SpringBootTest(classes = YxueApp.class)
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testredis() {
        /*ValueOperations opsForValue = redisTemplate.opsForValue();
        opsForValue.set("name","小黑");

        System.out.println(opsForValue.get("name"));*/

        Set<String> keys = stringRedisTemplate.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }
    }

    @Test
    public void test0() {
        ValueOperations opsForValue = redisTemplate.opsForValue();

/**
 * 存用户登录手机验证码的key如何设计?
 *  登录用户名-phone-code
 */

        opsForValue.set("admin-key","123456",5, TimeUnit.MINUTES);
    }
}
