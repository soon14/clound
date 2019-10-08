package com.cloud.portal.point.scheme.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.common.core.util.R;
import com.cloud.portal.point.scheme.model.PointScheme;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019/7/15
 * @description:积分方案管理
 * @modified By:lvlinc
 */
public interface PointSchemeService extends IService<PointScheme> {

    /**
     *获取方案列表分页信息
     *
     * @param page
     * @param pointScheme
     * @return
     */
    IPage<PointScheme> findPage(IPage<PointScheme> page, @Param("query") PointScheme pointScheme);

    /**
     *积分周期详情查询
     *
     * @param id
     * @return PointScheme
     */
    List<PointScheme> cycleList (String id);

    /**
     *获取当前运行周期
     *
     * @param
     * @return PointScheme
     */
    PointScheme starCycle ();

    /**
     *获取当前运行周期
     *
     * @param pointScheme
     * @return PointScheme
     */
    PointScheme areaStarCycle (PointScheme pointScheme);

    /**
     * 新增积分方案信息
     * @param pointScheme
     * @return
     */
    R saveScheme(PointScheme pointScheme);

    /**
     * 更新积分方案信息
     * @param pointScheme
     * @return
     */
    R updateScheme(PointScheme pointScheme);

    /**
     * 根据主键ID、更新积分方案 启用停用状态
     * 更新状态（启用 -停用状态）
     *
     * @param id
     * @param status
     * @return
     */
    R updateStatus(String id, String status);

    /**
     * 重置积分
     *
     * @param
     * @return
     */
    boolean resetScore();

    /**
     * 删除积分
     * @param
     * @return
     */
    boolean deleteScore(String id);

    /**
     * 删除方案
     *
     * @param id 方案id
     * @return
     */
    R removePointScheme(String id);

}
