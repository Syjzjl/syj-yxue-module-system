package com.baizhi.common;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class QiniuYunUtil {
    public static void main(String[] args) throws Exception{
        //upload();
    }

    public static String upload(String path,String h,String k) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "CNiExBoUVKkJi83ofiDpRd9lZZmiDePq_irAzLu0";
        String secretKey = "OeiNiUcc_KZWml3C6idWZpNGxaLpXNGTWb-KN62T";
        String bucket = "dingxiaofanshishabi";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = path;
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = k;

        Auth auth = Auth.create(accessKey, secretKey);
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{'hash':'"+h+"','key':'"+key+"'}");
        long expireSeconds = 3600;
        String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
        String upToken1 = auth.uploadToken(bucket);
        String aa = null;

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            //key的值
            aa = putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return aa;
    }
    public static void deleteFileFromQiniu(String fileName){

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        //...其他参数参考类注释
        String accessKey = "CNiExBoUVKkJi83ofiDpRd9lZZmiDePq_irAzLu0";
        String secretKey = "OeiNiUcc_KZWml3C6idWZpNGxaLpXNGTWb-KN62T";
        String bucket = "dingxiaofanshishabi";
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}
