package com.cloud.portal.archive.lvye.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.archive.lvye.model.RoutineCheck;
import java.util.List;

/**
 * @author wengshij
 * @date Created in 2019/5/23 14:01
 * @description: 日常检查信息
 * @modified By:wengshij
 */
public interface RoutineCheckService extends IService<RoutineCheck> {

    /**
     * 旅馆 日常检查信息分页
     * 根据当前旅馆单位编码 获取当前旅馆日常检查信息列表
     *
     * @param page
     * @param routineCheck
     * @return
     */
    IPage<List<RoutineCheck>> findPage(Page page, RoutineCheck routineCheck);
}
