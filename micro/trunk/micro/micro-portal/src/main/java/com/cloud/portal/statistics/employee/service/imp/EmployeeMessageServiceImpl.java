package com.cloud.portal.statistics.employee.service.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.common.data.datascope.DataScope;
import com.cloud.common.data.util.DataScopeUtil;
import com.cloud.portal.statistics.employee.mapper.EmployeeMessageMapper;
import com.cloud.portal.statistics.employee.model.EmployeeMessage;
import com.cloud.portal.statistics.employee.service.EmployeeMessageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Molly
 * @date Created in 2019/8/9
 * @description: 统计查询-统计报表-从业人员信息service实现
 * @modified By: Molly
 */
@Service
public class EmployeeMessageServiceImpl extends ServiceImpl<EmployeeMessageMapper, EmployeeMessage> implements EmployeeMessageService {

    /**
     * 查询列表（分页）
     * @param page
     * @param employeeMessage
     * @return
     */
    @Override
    public IPage<EmployeeMessage> findPage(IPage<EmployeeMessage> page, EmployeeMessage employeeMessage) {
        if(employeeMessage!=null&& StringUtils.isNotBlank(employeeMessage.getGxqh())){
            employeeMessage.setGxqh(employeeMessage.getGxqh().replaceAll("0*$", ""));
        }
        return this.baseMapper.findPage(page,employeeMessage);
    }

    /**
     * 根据来源系统获取从业人员信息列表
     * @param sourceType
     * @param page
     * @param employeeMessage
     * @return
     */
    @Override
    public IPage<EmployeeMessage> findPageBySourceType(String sourceType, IPage<EmployeeMessage> page, EmployeeMessage employeeMessage) {
        IPage<EmployeeMessage> result = null;
        if(StringUtils.isNotBlank(sourceType)){
            employeeMessage.setDataScope(DataScopeUtil.getDataScopeSql(new DataScope("a.gxdw","")));
            employeeMessage.setSourceType(sourceType);
            result = this.baseMapper.findPageBySourceType(page,employeeMessage);
        }else {
            throw new NullPointerException("来源系统不能空");
        }
        return result;
    }
}
