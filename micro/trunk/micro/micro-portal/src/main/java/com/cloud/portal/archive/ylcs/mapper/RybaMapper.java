package com.cloud.portal.archive.ylcs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.archive.ylcs.entity.Ryba;
import org.apache.ibatis.annotations.Param;



/**
 * @author ryt
 * @date Created in 2019/5/9 19:05
 * @description: 娱乐场所-人员备案mapper层
 * @modified By:
 */
public interface RybaMapper extends BaseMapper<Ryba> {

    /**
     * 获取人员备案列表
     * @param page
     * @param ryba
     * @return
     */
    IPage<Ryba> findRybaPage(IPage<Ryba> page, @Param("query") Ryba ryba);

    /**
     * 获取单位从业人员数量
     * @param compId
     * @return
     */
    Ryba getRybaCount(@Param("compId") String compId,@Param("csbabh") String csbabh);

    /**
     * 通过ID查询人员的详细信息
     *
     * @param rybaId
     * @return 人员详细信息
     */
    Ryba getById(@Param("rybaId")String rybaId);

    /**
     * 获取娱乐场所从业人员 照片信息
     * @param rybaZp 照片ID
     * @return
     */
    Ryba findRyImgById(@Param("rybaZp") String rybaZp);
}
