package com.cloud.portal.archive.lvye.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.archive.lvye.mapper.EmployeeMapper;
import com.cloud.portal.archive.lvye.model.Employee;
import com.cloud.portal.archive.lvye.service.EmployeeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wengshij
 * @date Created in 2019/5/22 9:11
 * @description: 旅馆业从业人员信息
 * @modified By:wengshij
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Override
    public IPage<List<Employee>> findPage(Page page, Employee employee) {
        return baseMapper.findPage(page, employee);
    }

    @Override
    public Map monthCount(String unitId) {
        Map<String, Integer> result = new HashMap<>(2);
        String incName = "inc", quitName = "quit", columnType = "type", numName = "num";
        result.put(incName, 0);
        result.put(quitName, 0);
        baseMapper.monthCount(unitId).forEach(map -> {
            if (incName.equals(map.get(columnType))) {
                result.put(incName, result.get(incName) + Integer.parseInt(map.get(numName).toString()));
            } else if (quitName.equals(map.get(columnType))) {
                result.put(quitName, result.get(quitName) + Integer.parseInt(map.get(numName).toString()));
            }
        });
        return result;
    }


    @Override
    public Employee findImgById(String id, String code) {
        if(StringUtils.isNotBlank(code)){
            code=code.trim();
        }
        return baseMapper.findImgById(id,code);
    }
}
