//package com.baizhi.controller;
//
//import com.baizhi.entity.City;
//import com.baizhi.entity.Common;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//
//@RestController
//@RequestMapping("echarts")
//public class EchartsController {
//
//
//
//    @RequestMapping("queryUser")
//    public HashMap<String, Object> queryUser(){
//
//        HashMap<String, Object> map = new HashMap<>();
//
//        map.put("month", Arrays.asList("1月","2月","3月","4月","5月","6月"));
//        map.put("boys", Arrays.asList(56,56,34,23,23,89));
//        map.put("girls", Arrays.asList(10,37,58,34,88,22));
//
//        return map;
//    }
//
//
//    @RequestMapping("queryMap")
//    public ArrayList<Common> queryMap(){
//
//        ArrayList<City> boyList = new ArrayList<>();
//        boyList.add(new City("北京",100));
//        boyList.add(new City("河北",60));
//        boyList.add(new City("湖南",70));
//        boyList.add(new City("山西",30));
//        boyList.add(new City("山东",30));
//
//        ArrayList<City> girlList = new ArrayList<>();
//        boyList.add(new City("河北",20));
//        boyList.add(new City("山东",60));
//        boyList.add(new City("海南",70));
//        boyList.add(new City("内蒙古",30));
//        boyList.add(new City("山西",30));
//
//        ArrayList<Common> commons = new ArrayList<Common>();
//        commons.add(new Common("小男孩",boyList));
//        commons.add(new Common("小姑娘",girlList));
//
//        return commons;
//    }
//
//}
