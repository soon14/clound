package com.cloud.portal.point.index.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.common.core.util.R;
import com.cloud.portal.point.index.model.IndexTree;
import com.cloud.portal.point.index.model.PointIndex;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wengshij
 * @date Created in 2019/6/20 10:11
 * @description: 积分指标（手动与自动）
 * @modified By:wengshij
 */
public interface PointIndexService extends IService<PointIndex> {
    /**
     * 新增指标信息
     *
     * @param pointIndex
     * @return
     */
    R saveIndex(PointIndex pointIndex);

    /**
     * 更新指标信息
     *
     * @param pointIndex
     * @return
     */
    R updateIndex(PointIndex pointIndex);

    /**
     * 判断唯一标识符是否存在
     * 判断规则（当前记录ID和当前记录标识符ID、去除已经删除后的标识）
     *
     * @param id   主键ID
     * @param udId 标识符ID
     * @return
     */
    Boolean udIdExit(String id, String udId);

    /**
     * 根据主键ID删除指标内容（逻辑删除指标）
     *
     * @param id
     * @return
     */
    R removeById(String id);

    /**
     * 根据主键ID、更新指标 启用停用状态
     * 更新状态（启用 -停用状态）
     *
     * @param id
     * @param status
     * @return
     */
    R updateStatus(String id, String status);

    /**
     * 获取指标列表分页信息
     *
     * @param page
     * @param pointIndex
     * @return
     */
    IPage<PointIndex> findPage(IPage<PointIndex> page,  PointIndex pointIndex);

    /**
     * 获取指标列表信息
     * @param pointIndex
     * @return
     */
    List<PointIndex> findList(PointIndex pointIndex);

    /**
     * 获取指标树形结构
     * @param indexType 索引类型
     * @param placeType 场所类型
     * @return
     */
    List<IndexTree>  getIndexTree(String indexType,String placeType);

    /**
     * 根据指标唯一标识和版本信息获取唯一指标信息
     *
     * @param udId    标识符信息
     * @param version 版本信息
     *                标识符 和版本信息 构成唯一主键
     * @return
     */
    PointIndex findIndexByUdIdAndVersion( String udId,  String version);

}
