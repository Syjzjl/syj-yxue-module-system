package com.baizhi.dao;

import com.baizhi.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaozhi
 * @since 2020-09-24
 */
public interface CommentMapper extends BaseMapper<Comment> {

    List<Comment> selectComment(@Param("begin") Integer begin, @Param("rows") Integer rows);

    List<Comment>  selectTwoComment(@Param("begin") Integer begin, @Param("rows") Integer rows,@Param("id") String id);
}
