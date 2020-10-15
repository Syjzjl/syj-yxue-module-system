package com.baizhi.app;

import com.baizhi.common.MD5Util;
import com.baizhi.common.QiniuYunUtil;
import com.baizhi.dto.VideoDTO;
import com.baizhi.dto.VideoDTO2;
import com.baizhi.dto.VideoDTO3;
import com.baizhi.entity.Video;
import com.baizhi.service.VideoService;
import com.baizhi.viodes.TestViodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;



/**
 * 创建者：syj
 * 类的作用：
 * 创建时间：2020/10/10
 */
@RestController
@RequestMapping("app")
public class AppVideoController {

    @Autowired
    private VideoService videoService;
    @Autowired
    private HttpServletRequest request;
    // 用来产生随机验证码的
    private static Random rand = new Random();
    private static String[] str = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };


    //首页展示数据
    @GetMapping("queryByReleaseTime")
    public Map<String, Object> queryByReleaseTime() {
        List<Video> list = videoService.queryVideo();
        //封装结果
        List<VideoDTO> videoDTOS = new ArrayList<>();
        for (Video video : list) {
         VideoDTO videoDTO=new VideoDTO(
                 video.getId(),
                 video.getTitle(),
                 video.getCoverUrl(),
                 video.getVideoUrl(),
                 video.getCreateAt(),
                 video.getIntro(),
                 video.getLikeNum(),
                 video.getFcate().getName(),
                 video.getFuser().getHeadShow()
         );
         videoDTOS.add(videoDTO);
        }
        //System.out.println("视频"+videoDTOS);
        return result(videoDTOS);
    }

    //首页搜索视频
    @RequestMapping("queryByLikeVideoName")
    public Map<String, Object> queryByLikeVideoName(String content){

         List<Video> list=videoService.queryAppByVideo(content);
        List<VideoDTO2> videoDTOS2 = new ArrayList<>();
        for (Video video : list) {
            VideoDTO2 videoDTO2=new VideoDTO2(
                    video.getId(),
                    video.getTitle(),
                    video.getCoverUrl(),
                    video.getVideoUrl(),
                    video.getCreateAt(),
                    video.getIntro(),
                    video.getLikeNum(),
                    video.getFcate().getName(),
                    video.getFcate().getId(),
                    video.getFuser().getId(),
                    video.getFuser().getUsername()
            );
            videoDTOS2.add(videoDTO2);
        }
        return result(videoDTOS2);
    }

    //首页发布视频
    public Map<String, Object> save(String description, MultipartFile videoFile,String videoTitle,
                                    String categoryId,String userId)throws Exception{

        // 存放产生的随机数
        StringBuffer sms;
        // 生成三位数的随机数
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            buf.append(str[rand.nextInt(10)]);
        }
        sms = buf;
        // 获取当前时间
        Date now = new Date();
        // 格式化字符
        SimpleDateFormat date = new SimpleDateFormat("yyyy MM dd hh mm ss");
        String datestring = date.format(now);
        // 去除时间里的空格
        String nokongge = datestring.replaceAll(" ", "");
        String longid = nokongge + sms;
        long randomid = new Long(Long.parseLong(longid));

        //System.out.println("视频："+id);
        System.out.println(videoFile);
        //获取文件名
        String filename = UUID.randomUUID().toString() +videoFile.getOriginalFilename();
        System.out.println("上传视频文件名"+filename);

        //获取上传路径
        String path = request.getServletContext().getRealPath("/videos");
        videoFile.transferTo(new File(path + "/" + filename));

        //获取全限定名
        String pathPlus = path + "\\"+filename;

        //MD5解析视频码
        String fileMD5 = MD5Util.getFileMD5(new File(pathPlus));

        //交由七牛云管理视频
        String upload = QiniuYunUtil.upload(pathPlus,fileMD5,filename);

        String  one="F:\\Idea-workspace\\syj-yxue-module-system\\src\\main\\webapp\\videos\\";
        String   two=randomid + ".jpg";
        String picurl = one+two;//截图存放路径

        String  fileVideo="http://qhazgz70l.hn-bkt.clouddn.com//"+filename;
        System.out.println("图片的路径："+picurl);
        TestViodes.fetchFrame(fileVideo,picurl);

        //管理完毕删除本地保存文件
        File file = new File(pathPlus);
        file.delete();
        //补全video
        Video video = new Video();
        video.setIntro(description);
        video.setTitle(videoTitle);
        video.setCoverUrl("http://qhazgz70l.hn-bkt.clouddn.com//"+two);
        video.setVideoUrl("http://qhazgz70l.hn-bkt.clouddn.com//"+upload);
        video.setCreateAt(new Date());
        video.setCId(categoryId);
        video.setUserId(userId);
        video.setGrpId(null);
        videoService.save(video);
        return result(video);
    }

    //视频详细信息展示
    @RequestMapping("queryByVideoDetail")
    public Map<String, Object> queryByVideoDetail(String videoId,String cateId,String userId){
        System.out.println("视频id"+videoId);
        List<Video>  videoList=videoService.queryVideo(videoId,cateId,userId);
          //查询除此视频的其他视频
        List<Video>  videoList2=videoService.queryNoVideo(videoId);
        System.out.println("视频排除："+videoList2);
        List<VideoDTO3> videoDTO3List2 = new ArrayList<>();
        for (Video video : videoList2) {
            VideoDTO3 videoDTO3=new VideoDTO3(
                    video.getId(),
                    video.getTitle(),
                    video.getCoverUrl(),
                    video.getVideoUrl(),
                    video.getCreateAt(),
                    video.getIntro(),
                    video.getLikeNum(),
                    video.getFcate().getName(),
                    video.getFcate().getId(),
                    video.getFuser().getId()
            );
            videoDTO3List2.add(videoDTO3);
        }
        //视频详细信息
        List<VideoDTO3> videoDTO3List = new ArrayList<>();
        for (Video video : videoList) {

            VideoDTO3 videoDTO3=new VideoDTO3(
                    video.getId(),
                    video.getTitle(),
                    video.getCoverUrl(),
                    video.getVideoUrl(),
                    video.getCreateAt(),
                    video.getIntro(),
                    video.getLikeNum(),
                    video.getFcate().getName(),
                    video.getFcate().getId(),
                    video.getFuser().getId(),
                    video.getFuser().getHeadShow(),
                    video.getFuser().getUsername(),
                    0,
                    true,
                    videoDTO3List2
            );
            videoDTO3List.add(videoDTO3);
        }
        System.out.println("视频详细信息："+videoDTO3List);
        System.out.println("其他视频："+videoDTO3List2);
        return result(videoDTO3List);
    }

    //返回结果
     public Map<String, Object> result(Object obj) {
         Map<String, Object> result = new HashMap<>();
          result.put("data", obj);
          result.put("message", "操作成功！~");
          result.put("status", 100);
          return result;
     }

}
