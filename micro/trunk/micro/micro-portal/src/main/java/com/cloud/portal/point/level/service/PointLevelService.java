package com.cloud.portal.point.level.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.common.core.util.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.point.level.model.PointLevel;
import com.cloud.portal.point.level.model.PointLevelChild;

import java.util.List;

/**
 * @author ryt
 * @date Created in 2019/6/21 10:11
 * @description: 等级评定方案
 * @modified By:
 */
public interface PointLevelService extends IService<PointLevel> {

    /**
     * 获取等级评定方案分页信息
     * @param page 分页信息
     * @param pointLevel 查询参数
     * @return
     */
    IPage<PointLevel> findPage(IPage<PointLevel> page, PointLevel pointLevel);

    /**
     * 根据方案id查询等级评定子项
     * @param levelId 方案id
     * @return
     */
     List<PointLevelChild> findChildList(String levelId);

    /**
     * 新增等级评定方案
     *
     * @param pointLevel
     * @return
     */
    R savePointLevel(PointLevel pointLevel);

    /**
     * 更新等级评定方案
     * @param pointLevel
     * @return
     */
    R updatePointLevel(PointLevel pointLevel);

    /**
     * 删除等级评定方案
     * @param id 方案id
     * @return
     */
    R removePointLevel(String id);

    /**
     * 等级评定方案状态更新
     * @param id 方案id
     * @param status 状态（启用、停用）
     * @return
     */
    R updateStatus(String id, String status);

    /**
     * 根据分值获取该地区、场所评定的等级
     * @param score 分值
     * @param placeType 所属场所
     * @param cityCode 所属地区
     * @return 等级、等级id
     */
    PointLevelChild getLevel(String placeType,String cityCode,Integer score);

}
