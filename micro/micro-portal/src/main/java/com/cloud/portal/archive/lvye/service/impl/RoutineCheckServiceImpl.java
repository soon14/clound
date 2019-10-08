package com.cloud.portal.archive.lvye.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.archive.lvye.mapper.RoutineCheckMapper;
import com.cloud.portal.archive.lvye.model.RoutineCheck;
import com.cloud.portal.archive.lvye.service.RoutineCheckService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wengshij
 * @date Created in 2019/5/23 14:04
 * @description: 旅馆日常检查信息
 * @modified By:wengshij
 */
@Service
public class RoutineCheckServiceImpl extends ServiceImpl<RoutineCheckMapper, RoutineCheck> implements RoutineCheckService {
    @Override
    public IPage<List<RoutineCheck>> findPage(Page page, RoutineCheck routineCheck) {
        return baseMapper.findPage(page, routineCheck);
    }
}
