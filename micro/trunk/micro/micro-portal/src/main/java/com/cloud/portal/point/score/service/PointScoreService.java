package com.cloud.portal.point.score.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.common.core.util.R;
import com.cloud.portal.point.detail.model.IndexDetail;
import com.cloud.portal.point.score.model.PointScore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wengshij
 * @date Created in 2019/7/24 9:59
 * @description: 特行场所历史分值
 * @modified By:wengshij
 */
public interface PointScoreService extends IService<PointScore> {

    /**
     * 重置当前周期分值、修改了指标 或者修改了等级方案后
     * 分值需要重置
     *
     * @return
     */
    R resetCurrentCycleScore();

    /**
     * 获取当前周期积分排名信息 和历史周期排名信息
     *
     * @param page
     * @param pointScore
     * @return
     */
    IPage<PointScore> findCycleScorePage(IPage<PointScore> page, PointScore pointScore);

    /**
     * 根据场所id获取当前周期积分排名信息
     *
     * @param placeId
     * @return
     */
    PointScore getCurrentCycleScoreByPlaceId(String placeId);

    /**
     * 判断等级评定 是否在历史分值中使用到
     *
     * @param levelIds 多个用逗号隔开
     * @return
     */
    boolean levelExitInScore(String levelIds);

    /**
     * 重置单个特行场所积分
     * @param pointScore
     */
    void resetPlaceScore(PointScore pointScore);


    /**
     * 积分撤回
     * @param indexDetail
     * @return
     */
    R rollBackScore(IndexDetail indexDetail);

    /**
     * 根据场所主键ID获取场所信息
     * @param placeId
     * @return
     */
    PointScore findPlaceScore(String placeId);

    /**
     * 获取历史记录信息（包括当前周期和历史周期）
     *
     * @param placeId
     * @return
     */
    List<PointScore> getPlaceAllScore( String placeId);

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

     */
    void resetAllScore();

    /**
     * 获取周期列表 前十条 不分页
     * @param pointScore
     * @return
     */
    List<PointScore> findCycleScoreList( @Param("query") PointScore pointScore);



}
