package com.cloud.portal.point.index.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.point.index.model.PointIndex;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wengshij
 * @date Created in 2019/6/20 10:10
 * @description: 积分指标数据访问层
 * @modified By:wengshij
 */
public interface PointIndexMapper extends BaseMapper<PointIndex> {

    /**
     * 判断唯一标识符是否存在
     * 判断规则（当前记录ID和当前记录标识符ID、去除已经删除后的标识）
     *
     * @param id   主键ID
     * @param udId 标识符ID
     * @return
     */
    Integer udIdExit(@Param("id") String id, @Param("udId") String udId);

    /**
     * 根据主键ID 更新指标的状态、（启用或者停用）
     *
     * @param pointIndex
     * @return
     */
    Integer updateStatus(PointIndex pointIndex);

    /**
     * 获取指标列表分页信息
     *
     * @param page
     * @param pointIndex
     * @return
     */
    IPage<PointIndex> findPage(IPage<PointIndex> page, @Param("query") PointIndex pointIndex);

    /**
     * 获取指标列表信息
     *
     * @param pointIndex
     * @return
     */
    List<PointIndex> findList(@Param("query") PointIndex pointIndex);

    /**
     * 根据指标唯一标识和版本信息获取唯一指标信息
     *
     * @param udId    标识符信息
     * @param version 版本信息
     *                标识符 和版本信息 构成唯一主键
     * @return
     */
    PointIndex findIndexByUdIdAndVersion(@Param("udId") String udId, @Param("version") String version);


}
