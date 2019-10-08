package com.cloud.portal.archive.jingz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.archive.jingz.model.EmployeeJz;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author wengshij
 * @date Created in 2019/5/22 9:11
 * @description: 警综从业人员信息
 * @modified By:wengshij
 */
public interface EmployeeJzService extends IService<EmployeeJz> {

    /**
     * 警综治安管辖单位从业人员信息分页
     * 根据当前单位主键ID 获取当前警钟治安管辖单位从业人员列表
     * 对应从业人员主表编号（zbbh）
     *
     * @param page
     * @param employee
     * @return
     */
    IPage<List<EmployeeJz>> findPage(Page page, EmployeeJz employee);

    /**
     * 根据单位ID、统计当前月份 离岗 上岗 人数
     * @param unitId
     * @return
     */
    Map monthCount(String unitId);

    /**
     * 根据id获取从业人员信息
     * @param id
     * @return
     */
    EmployeeJz findById(String id);

}
