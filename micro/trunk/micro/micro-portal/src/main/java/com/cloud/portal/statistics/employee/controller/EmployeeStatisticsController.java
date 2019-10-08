package com.cloud.portal.statistics.employee.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.api.vo.TreeUtil;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.statistics.employee.model.EmployeeMessage;
import com.cloud.portal.statistics.employee.model.EmployeeStatistics;
import com.cloud.portal.statistics.employee.service.EmployeeMessageService;
import com.cloud.portal.statistics.employee.service.EmployeeStatisticsService;
import com.cloud.portal.statistics.employee.service.IllegalityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 * @author Molly
 * @date Created in 2019/8/7
 * @description: 统计查询-统计报表-从业人员情况分析控制层
 * @modified By: Molly
 */
@RestController
@AllArgsConstructor
@RequestMapping("/statistics/employee")
public class EmployeeStatisticsController {

    @Autowired
    private EmployeeStatisticsService employeeStatisticsService;
    @Autowired
    private EmployeeMessageService employeeMessageService;
    @Autowired
    private IllegalityService illegalityService;

    /**
     * 获取从业人员统计列表
     * @param employeeStatistics
     * @return
     */
    @GetMapping("/tree_list")
    @SysLog("获取从业人员统计列表")
    public R treeList(EmployeeStatistics employeeStatistics){
        return new R<>(TreeUtil.bulid(employeeStatisticsService.treeList(employeeStatistics),"0"));
    }

    /**
     * 获取从业人员信息列表
     * @param page
     * @param employeeMessage
     * @return
     */
    @GetMapping("/message/page")
    @SysLog("获取从业人员列表")
    public R employeeList(Page page,EmployeeMessage employeeMessage){
        return new R<>(employeeMessageService.findPage(page,employeeMessage));
    }

    /**
     * 根据来源系统获取从业人员信息列表
     * @param page
     * @param employeeMessage
     * @return
     */
    @GetMapping("/message/source_type/page")
    @SysLog("根据来源系统获取从业人员列表")
    public R pageBySourceType(Page page,EmployeeMessage employeeMessage){
        return new R<>(employeeMessageService.findPageBySourceType(employeeMessage.getSourceType(),page,employeeMessage));
    }

    /**
     * 根据身份证号获取违法犯罪列表
     * @param zjhm
     * @return
     */
    @GetMapping("/illegality/list/{zjhm}")
    @SysLog("根据身份证号获取违法犯罪列表")
    public R illegalityList(@PathVariable(value = "zjhm") String zjhm){
        return new R<>(illegalityService.findListByZJHM(zjhm));
    }



}
