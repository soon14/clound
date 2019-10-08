package com.cloud.portal.statistics.employee.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.statistics.employee.mapper.EmployeeStatisticsMapper;
import com.cloud.portal.statistics.employee.model.EmployeeStatistics;
import com.cloud.portal.statistics.employee.service.EmployeeStatisticsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Molly
 * @date Created in 2019/6/24
 * @description: 统计查询-统计报表-从业人员情况分析service实现
 * @modified By: Molly
 */
@Service
public class EmployeeStatisticsServiceImpl extends ServiceImpl<EmployeeStatisticsMapper, EmployeeStatistics> implements EmployeeStatisticsService {

    /**
     * 获取从业人员统计列表
     * @param employeeStatistics
     * @return
     */
    @Override
    public List<EmployeeStatistics> treeList(EmployeeStatistics employeeStatistics) {
        if(employeeStatistics!=null&& StringUtils.isNotBlank(employeeStatistics.getCode())){
            employeeStatistics.setCode(employeeStatistics.getCode().replaceAll("0*$", ""));
        }
        return this.baseMapper.treeList(employeeStatistics);
    }
}
