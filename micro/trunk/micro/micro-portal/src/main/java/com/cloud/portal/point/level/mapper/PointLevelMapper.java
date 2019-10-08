package com.cloud.portal.point.level.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.point.level.model.PointLevel;
import com.cloud.portal.point.level.model.PointLevelChild;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ryt
 * @date Created in 2019/6/21 10:10
 * @description: 等级评定方案
 * @modified By:
 */
public interface PointLevelMapper extends BaseMapper<PointLevel> {


    /**
     * 获取等级评定方案分页信息
     *
     * @param page 分页信息
     * @param pointLevel 查询参数
     * @return
     */
    IPage<PointLevel> findPage(IPage<PointLevel> page, @Param("query")PointLevel pointLevel);
    /**
     * 保存等级评定子项
     *
     * @param childList
     * @return
     */
    boolean saveAllChild(@Param("childList") List<PointLevelChild> childList);
    /**
     * 根据方案id删除等级评定子项
     *
     * @param levelId
     * @return
     */
    void removeChild(@Param("levelId")String levelId);

    /**
     * 根据方案id查询等级评定子项
     *
     * @param levelId 方案id
     * @return
     */
    List<PointLevelChild> findChildList(@Param("levelId")String levelId);

    /**
     * 等级评定方案状态更新
     * @param pointLevel
     * @return
     */
    Integer updateStatus(PointLevel pointLevel);

    /**
     * 启动前校验-查询当前是否存在启动状态的评定方案
     * 判断规则（只允许启动一条通用的评定方案，各地市、场所也只允许存在一条状态为启动的评定方案）
     *
     * @param pointLevel
     * @return
     */
    Integer checkStatus(@Param("query") PointLevel pointLevel);

    /**
     * 根据分值获取该地区、场所评定的等级
     * @param placeType 场所类型
     * @param cityCode 区域编码
     * @param score 分值
     * @return 等级、等级id
     */
    String getLevel(@Param("placeType")String placeType,@Param("cityCode")String cityCode,@Param("score")Integer score);
}
