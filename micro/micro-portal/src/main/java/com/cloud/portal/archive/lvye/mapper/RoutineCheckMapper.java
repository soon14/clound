package com.cloud.portal.archive.lvye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.portal.archive.lvye.model.Employee;
import com.cloud.portal.archive.lvye.model.RoutineCheck;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wengshij
 * @date Created in 2019/5/23 13:57
 * @description: 旅馆日常检查 数据层
 * @modified By:wengshij
 */
public interface RoutineCheckMapper extends BaseMapper<RoutineCheck> {

    /**
     * 旅馆 日常检查信息分页
     * 根据当前旅馆单位编码 获取当前旅馆日常检查信息列表
     *
     * @param page
     * @param routineCheck
     * @return
     */
    IPage<List<RoutineCheck>> findPage(Page page, @Param("query") RoutineCheck routineCheck);

}
