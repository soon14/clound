package com.cloud.portal.archive.ylcs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.archive.ylcs.entity.RyLog;


/**
 * @author ryt
 * @date Created in 2019/5/17 16:56
 * @description: 娱乐场所-人员日志（人员考勤）service层
 * @modified By:
 */
public interface RyLogService extends IService<RyLog> {

    /**
     * 获取人员考勤信息列表
     *
     * @param page,ryLog
     * @return 人员考勤信息
     */
    IPage<RyLog> findRyLogPage(IPage<RyLog> page, RyLog ryLog);

    /**
     * 通过ID查询人员的详细信息
     *
     * @param rylogId
     * @return 人员详细信息
     */
    RyLog getById(String rylogId);
}
