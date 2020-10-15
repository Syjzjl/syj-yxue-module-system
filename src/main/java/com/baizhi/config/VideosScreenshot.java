//package com.baizhi.config;
//
//import java.io.InputStream;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//
///**
// * 创建者：syj
// * 类的作用：视频截取封面图类
// * 创建时间：2020/9/30
// */
//public class VideosScreenshot {
//    // 用来产生随机验证码的
//    private static Random rand = new Random();
//
//    private static String[] str = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
//
//    /**
//     *
//     * @param videourl 视频截图
//     * @param
//     * @return
//     */
//    public String vidpicirl(String videourl) {
//
//        System.err.println(videourl);
//
//        // 存放产生的随机数
//        StringBuffer sms;
//        // 生成三位数的随机数
//        StringBuffer buf = new StringBuffer();
//        for (int i = 0; i < 3; i++) {
//            buf.append(str[rand.nextInt(10)]);
//        }
//        sms = buf;
//        // 获取当前时间
//        Date now = new Date();
//        // 格式化字符
//        SimpleDateFormat date = new SimpleDateFormat("yyyy MM dd hh mm ss");
//        String datestring = date.format(new Date());
//        // 去除时间里的空格
//        String nokongge = datestring.replaceAll(" ", "");
//        String longid = nokongge + sms;
//        long randomid = new Long(Long.parseLong(longid));
//
//        String picurl = "F:\\Idea-workspace\\syj-yxue-module-system\\src\\main\\webapp\\videos\\"
//                + randomid + ".jpg";//截图存放路径
//
//        //String picurl = "E:\\"+randomid+".jpg";
//        List<String> commend = new java.util.ArrayList<String>();
//        commend.add("F:\\ffmpeg.exe");//插件存放路径
//        commend.add("-i");
//        commend.add(videourl);
//        commend.add("-y");
//        commend.add("-f");
//        commend.add("image2");
//        commend.add("-ss");
//        commend.add("1");
//        commend.add("-t");
//        commend.add("0.001");
//        commend.add("-s");
//        commend.add("350*240");
//        commend.add(picurl);
//        try {
//            ProcessBuilder builder = new ProcessBuilder();
//            builder.command(commend);
//            builder.redirectErrorStream(true);
//            System.out.println("视频截图开始...");
//            // builder.start();
//            Process process = builder.start();
//            InputStream in = process.getInputStream();
//            byte[] re = new byte[1024];
//            System.out.print("正在进行截图，请稍候");
//            while (in.read(re) != -1) {
//                System.out.print(".");
//            }
//            System.out.println("");
//            in.close();
//            System.err.println("视频截图完成...");
//            System.err.println("视频截图url:" + picurl);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("视频截图失败！");
//        }
//        return picurl;
//    }
//}
