package com.cloud.portal.statistics.employee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.portal.statistics.employee.model.EmployeeStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Molly
 * @date Created in 2019/8/7
 * @description: 统计查询-统计报表-从业人员情况分析mapper
 * @modified By: Molly
 */
public interface EmployeeStatisticsMapper extends BaseMapper<EmployeeStatistics> {

    /**
     * 获取从业人员统计列表
     * @param employeeStatistics
     * @return
     */
    public List<EmployeeStatistics> treeList(@Param(value = "query") EmployeeStatistics employeeStatistics);

}
