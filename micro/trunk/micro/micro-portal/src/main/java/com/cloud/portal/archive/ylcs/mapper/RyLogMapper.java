package com.cloud.portal.archive.ylcs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.archive.ylcs.entity.RyLog;
import org.apache.ibatis.annotations.Param;


/**
 * @author ryt
 * @date Created in 2019/5/17 16:56
 * @description: 娱乐场所-人员日志（人员考勤）mapper层
 * @modified By:
 */
public interface RyLogMapper extends BaseMapper<RyLog> {

    /**
     * 获取人员考勤列表
     * @param page
     * @param ryLog
     * @return
     */
    IPage<RyLog> findRyLogPage(IPage<RyLog> page, @Param("query") RyLog ryLog);


    /**
     * 通过ID查询人员的详细信息
     *
     * @param rylogId
     * @return 人员详细信息
     */
    RyLog getById(@Param("rylogId") String rylogId);
}
