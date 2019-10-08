package com.cloud.portal.point.scheme.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.point.scheme.model.Cycle;
import com.cloud.portal.point.scheme.model.PointScheme;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019/7/15
 * @description:积分方案管理数据访问层
 * @modified By:lvlinc
 */
@Mapper
public interface PointSchemeMapper extends BaseMapper<PointScheme> {

    /**
     *查询方案id获取当前周期外所有历史分数 保存至历史表
     * @param pointScheme
     * @return boolean
     */
    boolean savePointScore(PointScheme pointScheme);

    /**
     *查询方案id获取当前方案周期历史分数 保存至历史表
     * @param pointScheme
     * @return boolean
     */
    boolean saveSchemeScore(PointScheme pointScheme);

    /**
     *重置所有历史周期分数
     * @param pointScheme
     * @return boolean
     */
    boolean resetScore(PointScheme pointScheme);

    /**
     *重置该方案历史周期分数
     * @param pointScheme
     * @return boolean
     */
    boolean resetSchemeScore(PointScheme pointScheme);

    /**
     * 更新周期分数
     * @param pointScheme
     * @return boolean
     */
    boolean updateScore(PointScheme pointScheme);

    /**
     * 启用的方案
     *
     * @param pointScheme
     * @return int
     */
    int checkStatus(@Param("query")PointScheme pointScheme);
    /**
     * 保存周期详情
     *
     * @param cycle
     * @return boolean
     */
    boolean saveEdit(Cycle cycle);

    /**
     * 删除周期详情
     *
     * @param id
     * @return boolean
     */
    boolean removeEdit(String id);

    /**
     * 逻辑删除方案
     *
     * @param id
     * @return boolean
     */
    boolean removePointScheme(String id);

    /**
     * 保存方案
     *
     * @param pointScheme
     * @return
     */
    int saveScheme(PointScheme pointScheme);

    /**
     * 根据主键ID 更新状态、（启用或者停用）
     *
     * @param pointScheme
     * @return
     */
    Integer updateStatus(PointScheme pointScheme);

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
    PointScheme areaStarCycle (@Param("query")PointScheme pointScheme);

    /**
     *获取方案信息
     *
     * @param id
     * @return PointScheme
     */
    PointScheme getByid (String id);
}
