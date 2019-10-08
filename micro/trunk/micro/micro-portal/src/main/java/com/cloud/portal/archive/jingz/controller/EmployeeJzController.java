package com.cloud.portal.archive.jingz.controller;

/**
 * @author wengshij
 * @date Created in 2019/5/22 9:09
 * @description: 警综从业人员控制层
 * @modified By:wengshij
 */

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.portal.archive.jingz.model.EmployeeJz;
import com.cloud.portal.archive.jingz.service.EmployeeJzService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/jz/employee")
@Api(value = "jz", description = "警综系统-从业人员管理模块")
public class EmployeeJzController {
    private final EmployeeJzService employeeService;

    /**
     * 从业人员信息分页
     * 从业人员所属单位ID未必填项
     * （根据当前单位ID 获取所有从业人员信息）
     *
     * @param page     分页对象
     * @param employee 旅业 从业人员对象
     * @return
     */
    @GetMapping("/page")
    public R getPage(Page page, EmployeeJz employee) {
        return new R<>(employeeService.findPage(page, employee));
    }


    /**
     * 根据单位ID 统计当前月份 场所的从业人员 离岗人数 和在岗人数
     *
     * @param unitId 单位ID（旅馆编号）
     * @return
     */
    @GetMapping("/count/{unitId}")
    public R monthCount(@PathVariable String unitId) {
        return new R<>(employeeService.monthCount(unitId));
    }

    /**
     * 获取从业人员
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R findById(@PathVariable String id){
        return new R<>(employeeService.findById(id));
    }


}
