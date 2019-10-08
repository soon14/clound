package com.cloud.portal.archive.jingz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.archive.jingz.mapper.EmployeeJzMapper;
import com.cloud.portal.archive.jingz.model.EmployeeJz;
import com.cloud.portal.archive.jingz.service.EmployeeJzService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wengshij
 * @date Created in 2019/5/22 9:11
 * @description:警综从业人员信息
 * @modified By:wengshij
 */
@Service
public class EmployeeJzServiceImpl extends ServiceImpl<EmployeeJzMapper, EmployeeJz> implements EmployeeJzService {

    @Override
    public IPage<List<EmployeeJz>> findPage(Page page, EmployeeJz employee) {
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
                result.put(incName, Integer.parseInt(map.get(numName).toString()));
            } else if (quitName.equals(map.get(columnType))) {
                result.put(quitName,Integer.parseInt(map.get(numName).toString()));
            }
        });
        return result;

    }

    @Override
    public EmployeeJz findById(String id) {
        return this.baseMapper.findById(id);
    }
}
