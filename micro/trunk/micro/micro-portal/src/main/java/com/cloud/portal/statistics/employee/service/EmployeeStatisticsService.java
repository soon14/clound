package com.cloud.portal.statistics.employee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.statistics.employee.model.EmployeeStatistics;

import java.util.List;

/**
 * @author Molly
 * @date Created in 2019/6/24
 * @description: 统计查询-统计报表-从业人员情况分析service接口
 * @modified By: Molly
 * */
public interface EmployeeStatisticsService extends IService<EmployeeStatistics> {

    /**
     * 获取从业人员统计列表
     * @param employeeStatistics
     * @return
     */
    public List<EmployeeStatistics> treeList(EmployeeStatistics employeeStatistics);
}
