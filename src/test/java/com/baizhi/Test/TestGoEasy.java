package com.baizhi.Test;

import com.alibaba.fastjson.JSON;
import io.goeasy.GoEasy;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class TestGoEasy {


    @Test
    public void testGoEasy(){

        //初始化GoEasy   参数：地区地址，appKey
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-9ae9e05cdd2a4c568224055de1132131");

        //发送数据   参数：通道名称，发送的内容
        goEasy.publish("2004Channel", "Hello, GoEasy! 2004");
    }


    @Test
    public void testGoEasys(){

        for (int i = 0; i < 20; i++) {
            Random random = new Random();

            HashMap<String, Object> map = new HashMap<>();

            map.put("month", Arrays.asList("1月","2月","3月","4月","5月","6月"));
            map.put("boys", Arrays.asList(random.nextInt(100),random.nextInt(60),random.nextInt(30),random.nextInt(80),random.nextInt(50),random.nextInt(100)));
            map.put("girls", Arrays.asList(random.nextInt(100),random.nextInt(50),random.nextInt(80),random.nextInt(80),random.nextInt(50),random.nextInt(100)));

            //将map集合转为json字符串
            String str = JSON.toJSONString(map);


            //初始化GoEasy   参数：地区地址，appKey
            GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-9ae9e05cdd2a4c568224055de1132131");

            //发送数据   参数：通道名称，发送的内容
            goEasy.publish("2004Channel", str);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
