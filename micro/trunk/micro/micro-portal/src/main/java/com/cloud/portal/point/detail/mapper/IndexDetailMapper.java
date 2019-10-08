package com.cloud.portal.point.detail.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.point.detail.model.IndexDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author wengshij
 * @date Created in 2019/7/16 14:21
 * @description: 积分指标明细（包含自动积分指标和手动积分指标）
 * @modified By:wengshij
 */
public interface IndexDetailMapper extends BaseMapper<IndexDetail> {

    /**
     * 判断 指标索引是否已经被适用过
     * 通过pointType参数 如果等于0 则查询自动积分明细表
     * 等于 1 查询手动积分明细表 、其他查询手动积分和自动积分视图表
     *
     * @param indexDetail
     * @return
     */
    Integer indexExitInDetail(IndexDetail indexDetail);

    /**
     * 更新标识位、
     * 指标的撤回功能
     * 如果 通过pointType参数 如果等于 0 则只更改自动积分项
     * 如果 等于 1 则只更改手动积分明细表
     * 如果等于空 则两张表同时修改
     *
     * @param indexDetail
     * @return
     */
    Integer updateFlag(IndexDetail indexDetail);


    /**
     * 统计 当前周期 当前场所 指标项 使用次数 是否已上限
     *
     * @param indexDetail
     * @return
     */
    Integer countIndexUseNum(IndexDetail indexDetail);


    /**
     * 统计 场所分值 按场所及每个指标项统计
     *
     * @param page
     * @param indexDetail
     * @return
     */
    IPage<IndexDetail> countPage(IPage<IndexDetail> page, @Param("query") IndexDetail  indexDetail);


    /**
     * 积分明细分页
     *
     * @param page
     * @param indexDetail
     * @return
     */
    IPage<IndexDetail> detailPage(IPage<IndexDetail> page, @Param("query") IndexDetail  indexDetail);

    /**
     * 保存手动积分
     * @param indexDetail
     * @return
     */
    Integer savePassiveDetail(IndexDetail  indexDetail);

    /**
     * 统计手动积分总分值、因为手动积分存储的是一个总分 需要计算所有分值再求平均值
     * 才能计算出使用次数
     * @param indexDetail
     * @return
     */
    Integer countPassiveDetailTotalScore(IndexDetail  indexDetail);

    /**
     * 按积分类型统计当天新增积分记录总数
     * @return
     */
    List<Map<String,Object>> totalTodayDetail(@Param("query")IndexDetail indexDetail);

    /**
     * 获取最新10条自动积分记录
     * @return
     */
    List<IndexDetail> findNewestAutomaticList(@Param("query")IndexDetail indexDetail);

}
