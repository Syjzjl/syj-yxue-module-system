package com.baizhi.service.impl;

import com.baizhi.dao.VideoMapper;
import com.baizhi.entity.Video;
import com.baizhi.service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 创建者：syj
 * 类的作用：
 * 创建时间：2020/9/25
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VideoMapper videoMapper;


    @Override
    public List<Video> queryAllVideo(Integer page,Integer rows) {
        Integer  begin=rows*(page-1);
        List<Video> list=videoMapper.selectAllVideo(begin,rows);

        System.out.println("视频分页："+list);
        return list;
    }

    @Override
    public List<Video> queryByVideo(Video video, String searchString) {
        QueryWrapper<Video> queryWrapper=new QueryWrapper<>();
        List<Video> list=videoMapper.selectList(queryWrapper.like("title",searchString));

        return list;
    }

    @Override
    public Integer videoCount() {
        QueryWrapper<Video> queryWrapper=new QueryWrapper<>();
        Integer integer=videoMapper.selectCount(queryWrapper);
        return  integer;
    }

    //修改
    @Override
    public void updateVideo(Video video) {

        videoMapper.updateById(video);
    }
    //前台视频展示
    @Override
    public List<Video> queryVideo() {
        List<Video> list=videoMapper.selectVideo();
        return list;
    }
    //视频详细信息
    @Override
    public List<Video> queryVideo(String videoId, String cateId, String userId) {
        List<Video> list=videoMapper.selectAppVideo(videoId,cateId,userId);
        return list;
    }

    @Override
    public List<Video> queryNoVideo(String videoId) {
        List<Video> list=videoMapper.selectNoVideo(videoId);
        return list;
    }

    @Override
    public List<Video> queryAppByVideo(String content) {
        List<Video> list=videoMapper.selectAppByVideo(content);
        return list;
    }

    @Resource
    ElasticsearchTemplate elasticsearchTemplate;


    @Override
    public List<Video> querySearchVideo(String content) {

        //设置高亮的参数
        HighlightBuilder.Field field = new HighlightBuilder.Field("*")
                .preTags("<span style='color:red'>")
                .postTags("</span>")
                .requireFieldMatch(false);//开启多行高亮

        //设置查询条件
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("yingx") //指定索引
                .withTypes("video") //自定类型
                .withQuery(QueryBuilders.queryStringQuery(content).field("title").field("intro")) //设置查询条件
                .withHighlightFields(field) //设置高亮
                //.withFields("id","title","intro","coverUrl")//设置要哪些属性
                .build();

        AggregatedPage<Video> videos = elasticsearchTemplate.queryForPage(searchQuery, Video.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {

                ArrayList<Video> videos = new ArrayList<>();



                SearchHit[] hits = response.getHits().getHits();

                for (SearchHit hit : hits) {


                    //没有高亮的数据
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();

                    String id =sourceAsMap.get("id")!=null?sourceAsMap.get("id").toString():null;
                    String title =sourceAsMap.get("title")!=null?sourceAsMap.get("title").toString():null;
                    String intro =sourceAsMap.get("intro")!=null?sourceAsMap.get("intro").toString():null;
                    String coverUrl =sourceAsMap.get("coverUrl")!=null?sourceAsMap.get("coverUrl").toString():null;
                    String videoUrl =sourceAsMap.get("videoUrl")!=null?sourceAsMap.get("videoUrl").toString():null;
                    String userId =sourceAsMap.get("userId")!=null?sourceAsMap.get("userId").toString():null;
                    String cId =sourceAsMap.get("cId")!=null?sourceAsMap.get("cId").toString():null;
                    String grpId =sourceAsMap.get("grpId")!=null?sourceAsMap.get("grpId").toString():null;
                    //日期处理
                    Date dates=null;
                    if(sourceAsMap.get("grpId")!=null){
                        //处理日期的操作
                        String createAt = sourceAsMap.get("createAt").toString();
                        Long aLong = Long.valueOf(createAt);
                        dates= new Date(aLong);
                    }

                    //1.将未高亮的数据封装为Video对象
                    Video video = new Video();
                    video.setId(id);
                    video.setTitle(title);
                    video.setIntro(intro);
                    video.setCoverUrl(coverUrl);
                    video.setVideoUrl(videoUrl);
                    video.setCreateAt(dates);
                    video.setUserId(userId);
                    video.setCId(cId);
                    video.setGrpId(grpId);

                    //2.将原有未高亮的数据替换为高亮的结果

                    //高亮的数据
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();

                        /*HighlightField title1 = highlightFields.get("title");
                        Text[] fragments = title1.fragments();
                        String titles = fragments[0].toString();
                        System.out.println("------"+titles);*/

                    //------<span style='color:red'>橘子</span>
                    if (highlightFields.get("title") != null) {
                        String titles = highlightFields.get("title").fragments()[0].toString();
                        video.setTitle(titles);
                    }
                    if (highlightFields.get("intro") != null) {
                        String intros = highlightFields.get("intro").fragments()[0].toString();
                        video.setIntro(intros);
                    }

                    //将封装好的对象放入集合
                    videos.add(video);
                }
                //强转返回
                return new AggregatedPageImpl<T>((List<T>) videos);
            }
        });


        List<Video> videoList = videos.getContent();
        return videoList;
    }

}
