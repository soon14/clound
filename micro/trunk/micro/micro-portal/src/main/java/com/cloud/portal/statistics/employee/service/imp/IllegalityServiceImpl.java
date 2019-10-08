package com.cloud.portal.statistics.employee.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.statistics.employee.mapper.IllegalityMapper;
import com.cloud.portal.statistics.employee.model.Illegality;
import com.cloud.portal.statistics.employee.service.IllegalityService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Molly
 * @date Created in 2019/8/9
 * @description: 统计查询-统计报表-案件信息service实现
 * @modified By: Molly
 */
@Service
public class IllegalityServiceImpl extends ServiceImpl<IllegalityMapper, Illegality> implements IllegalityService {

    @Override
    public List<Illegality> findListByZJHM(String zjhm) {
        return this.baseMapper.findListByZJHM(zjhm);
    }
}
