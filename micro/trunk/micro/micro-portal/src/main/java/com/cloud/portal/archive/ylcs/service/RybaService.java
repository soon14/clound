package com.cloud.portal.archive.ylcs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.archive.ylcs.entity.Ryba;
import org.apache.ibatis.annotations.Param;


/**
 * @author ryt
 * @date Created in 2019/5/9 17:34
 * @description: 娱乐场所-人员备案service层
 * @modified By:
 */
public interface RybaService extends IService<Ryba> {

    /**
     * 获取人员信息列表
     *
     * @param page,ryba
     * @return 人员信息
     */
    IPage<Ryba> findRybaPage(IPage<Ryba> page, Ryba ryba);

    /**
     * 通过单位ID查询人员的在职离职数量
     *
     * @param compId
     * @return
     */
    Ryba getRybaCount(String compId,String csbabh);

    /**
     * 通过ID查询人员的详细信息
     *
     * @param rybaId
     * @return 人员详细信息
     */
    Ryba getById(String rybaId);

    /**
     * 获取娱乐场所从业人员 照片信息
     * @param rybaZp 照片ID
     * @return
     */
    Ryba findRyImgById(@Param("rybaZp") String rybaZp);
}
