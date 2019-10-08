package com.cloud.portal.statistics.employee.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.statistics.employee.model.EmployeeMessage;

import java.util.List;

/**
 * @author Molly
 * @date Created in 2019/8/9
 * @description: 统计查询-统计报表-从业人员信息service接口
 * @modified By: Molly
 * */
public interface EmployeeMessageService extends IService<EmployeeMessage> {

    /**
     * 查询列表（分页）
     * @param page
     * @param employeeMessage
     * @return
     */
    public IPage<EmployeeMessage> findPage(IPage<EmployeeMessage> page, EmployeeMessage employeeMessage);

    /**
     * 根据来源系统获取从业人员信息列表
     * @param sourceType
     * @param page
     * @param employeeMessage
     * @return
     */
    public IPage<EmployeeMessage> findPageBySourceType(String sourceType,IPage<EmployeeMessage> page,EmployeeMessage employeeMessage);

}
