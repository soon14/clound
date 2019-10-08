package com.cloud.portal.archive.lvye.controller;

/**
 * @author wengshij
 * @date Created in 2019/5/22 9:09
 * @description: 旅馆业从业人员控制层
 * @modified By:wengshij
 */

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.api.entity.SysLog;
import com.cloud.common.core.util.R;
import com.cloud.portal.archive.lvye.model.Employee;
import com.cloud.portal.archive.lvye.service.EmployeeService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@AllArgsConstructor
@RequestMapping("/ly/employee")
@Api(value = "ly", description = "旅业系统-从业人员管理模块")
public class EmployeeController {
    private final EmployeeService employeeService;

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
    public R getPage(Page page, Employee employee) {
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

    @GetMapping("/img/{code}/{id}")
    public void getImg(@PathVariable String code, @PathVariable String id, HttpServletResponse response) {
        try {
            InputStream in = null;
            Employee employee = employeeService.findImgById(id, code);
            if (null != employee && null != employee.getPicture()) {
                in = new ByteArrayInputStream(employee.getPicture());
            }
            response.setContentType("application/octet-stream; charset=UTF-8");
            IoUtil.copy(in, response.getOutputStream());
        } catch (Exception e) {
           //无关紧要的获取输出
        }
    }


}
