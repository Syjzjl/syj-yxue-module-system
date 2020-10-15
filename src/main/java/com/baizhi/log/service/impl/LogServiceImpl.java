package com.baizhi.log.service.impl;

import com.baizhi.log.entity.Log;
import com.baizhi.log.dao.LogMapper;
import com.baizhi.log.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author syj
 * @since 2020-09-28
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
