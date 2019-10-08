package com.cloud.portal.judged.lib.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.judged.lib.model.PlanLibrary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019/5/27
 * @description: 字典
 * @modified By:lvlinc
 */
@Mapper
public interface PlanLibraryMapper extends BaseMapper<PlanLibrary> {
    /**
     * 分页查询数据表字段
     * @param iPage    参数集
     * @param planLibrary 查询参数列表
     * @return Dict 数据表集合
     */
    IPage<List<PlanLibrary>> findPage(IPage<PlanLibrary> iPage, @Param("query") PlanLibrary planLibrary);

    boolean insertPlanLibrary(PlanLibrary planLibrary);

    List<PlanLibrary> selectByType(@Param("query")PlanLibrary planLibrary);
}
