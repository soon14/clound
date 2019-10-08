package com.cloud.portal.point.score.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.point.detail.model.IndexDetail;
import com.cloud.portal.point.score.model.PointScore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wengshij
 * @date Created in 2019/7/24 9:57
 * @description: 特行场所历史分值
 * @modified By:wengshij
 */
public interface PointScoreMapper extends BaseMapper<PointScore> {


    /**
     * 利用存储过程 重置当前积分
     * 只影响当前周期
     *
     * @param pointScore
     */
    void resetCurrentCycleScore(PointScore pointScore);

    /**
     * 重置关联 分数
     * 重置规则：积分以四大系统为基准、如果存在关联 所有警综分值均和四大系统一致
     * 只影响当前周期
     *
     * @param pointScore
     */
    void resetRelateScore(PointScore pointScore);

    /**
     * 获取当前周期积分排名信息和历史周期排名信息
     *
     * @param page
     * @param pointScore
     * @return
     */
    IPage<PointScore> findCycleScorePage(IPage<PointScore> page, @Param("query") PointScore pointScore);

    /**
     * 判断等级评定 是否在历史分值中使用到
     *
     * @param levelIds
     * @return
     */
    Integer levelExitInScore(@Param("levelIds") List<String> levelIds);

    /**
     * 重置单个特行场所积分
     *
     * @param pointScore
     */
    void resetPlaceScore(PointScore pointScore);

    /**
     * 根据场所主键ID获取场所信息
     *
     * @param placeId
     * @return
     */
    PointScore findPlaceScore(@Param("placeId") String placeId);

    /**
     * 获取历史记录信息（包括当前周期和历史周期）
     *
     * @param placeId
     * @return
     */
    List<PointScore> getPlaceAllScore(@Param("placeId") String placeId);

    /**
     * 重置所有分值(适用于定时任务、每天均可执行)
     * 一、如果当前周期不为空的时候
     * 1、将不是这个周期内的所有分值归属到历史表中
     * 2、将所有不是这个周期的特行场所初始化分值
     * 3、重新计算所有分值
     * 4、初始化所有关联的分值
     * 二、如果当前周期为空
     * 1、将所有周期不为空的分值 插入到历史表中
     * 2、清空分值
     *
     * @param cycleId   当前周期ID
     */
    void resetAllScore(@Param("cycleId") String cycleId, @Param("isInit") String isInit,@Param("defaultScore")Integer defaultScore);

    /**
     * 根据场所id获取当前周期积分排名信息
     *
     * @param placeId
     * @return
     */
    PointScore getCurrentCycleScoreByPlaceId(@Param("placeId") String placeId);

    /**
     * 获取周期列表 前十条 不分页
     * @param pointScore
     * @return
     */
    List<PointScore> findCycleScoreList( @Param("query") PointScore pointScore);


}
