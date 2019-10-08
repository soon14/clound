package com.cloud.portal.judged.analyze.machine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.judged.analyze.machine.model.DifferentPlacesRevamp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lvlinc
 * @date Created in 2019/7/10
 * @description:机修 同一车辆不同车行多次修改分析 (一个月内同一车辆不同车行修改3次以上)
 * @modified By:lvlinc
 */
@Mapper
public interface DifferentPlacesRevampMapper extends BaseMapper<DifferentPlacesRevamp> {
    /**
     * 同一车辆不同车行多次修改分析表分页信息 列表信息
     *
     * @param iPage
     * @param dpr
     * @return
     */
    IPage<DifferentPlacesRevamp> findPage(IPage<DifferentPlacesRevamp> iPage, @Param("query") DifferentPlacesRevamp dpr);

    /**
     * 同一车辆不同车行多次修改分析
     *
     * @param iPage
     * @param dpr
     * @return
     */
    IPage<DifferentPlacesRevamp> monthPage(IPage<DifferentPlacesRevamp> iPage, @Param("query") DifferentPlacesRevamp dpr);

    /**
     * 同一车辆不同车行多次修改分析表分页详情信息 列表信息
     *
     * @param iPage
     * @param dpr
     * @return
     */
    IPage<DifferentPlacesRevamp> oneToMany(IPage<DifferentPlacesRevamp> iPage, @Param("query") DifferentPlacesRevamp dpr);
}
