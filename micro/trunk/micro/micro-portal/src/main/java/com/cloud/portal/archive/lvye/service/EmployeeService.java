package com.cloud.portal.archive.lvye.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.archive.lvye.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author wengshij
 * @date Created in 2019/5/22 9:11
 * @description: 旅馆业从业人员信息
 * @modified By:wengshij
 */
public interface EmployeeService extends IService<Employee> {

    /**
     * 旅馆从业人员信息分页
     * 根据当前旅馆单位编码 获取当前旅馆从业人员列表
     *
     * @param page
     * @param employee
     * @return
     */
    IPage<List<Employee>> findPage(Page page, Employee employee);

    /**
     * 根据单位ID、统计当前月份 离岗 上岗 人数
     * @param unitId
     * @return
     */
    Map monthCount(String unitId);

    /**
     * 获取旅馆业从业人员 照片信息
     * @param id 主键ID
     * @param code 地市代码
     * @return
     */
    Employee findImgById(@Param("id") String id, @Param("code") String code);
}
