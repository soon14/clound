package com.cloud.portal.statistics.employee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.statistics.employee.model.EmployeeMessage;
import org.apache.ibatis.annotations.Param;

/**
 * @author Molly
 * @date Created in 2019/8/9
 * @description: 统计查询-统计报表-从业人员信息mapper
 * @modified By: Molly
 */
public interface EmployeeMessageMapper extends BaseMapper<EmployeeMessage> {

    /**
     * 查询列表（分页）
     * @param page
     * @param employeeMessage
     * @return
     */
    public IPage<EmployeeMessage> findPage(IPage<EmployeeMessage> page, @Param(value = "query") EmployeeMessage employeeMessage);

    /**
     * 根据来源系统获取从业人员信息列表
     * @param page
     * @param employeeMessage
     * @return
     */
    public IPage<EmployeeMessage> findPageBySourceType(IPage<EmployeeMessage> page,@Param(value = "query") EmployeeMessage employeeMessage);
}
