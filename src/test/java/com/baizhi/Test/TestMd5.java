package com.baizhi.Test;

import com.baizhi.common.MD5Util;

import java.io.File;

public class TestMd5 {
    public static void main(String[] args) {
        String fileMD5 = MD5Util.getFileMD5(new File("F:\\Idea-workspace\\syj-yxue-module-system\\src\\main\\webapp\\videos\\2.mp4"));
        System.out.println(fileMD5);
    }
}
