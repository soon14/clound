package com.cloud.portal.archive.ylcs.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.archive.ylcs.entity.RyLog;
import com.cloud.portal.archive.ylcs.mapper.RyLogMapper;
import com.cloud.portal.archive.ylcs.service.RyLogService;
import org.springframework.stereotype.Service;

/**
 * @author ryt
 * @date Created in 2019/5/17 16:56
 * @description: 娱乐场所-人员日志（人员考勤）service层实现
 * @modified By:
 */
@Service
public class RyLogServiceImpl extends ServiceImpl<RyLogMapper, RyLog> implements RyLogService {
    /**
     * 获取人员考勤信息列表
     *
     * @param page,ryLog
     * @return 人员考勤信息
     */
    @Override
    public IPage<RyLog> findRyLogPage(IPage<RyLog> page, RyLog ryLog) {
      return baseMapper.findRyLogPage(page,ryLog);
    }
    /**
     * 通过ID查询人员的详细信息
     *
     * @param rylogId
     * @return 人员详细信息
     */
    @Override
    public RyLog getById(String rylogId){
        return this.baseMapper.getById(rylogId);
    }
}
