package com.cloud.portal.archive.mechanical.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.archive.mechanical.model.Cjcl;
import org.apache.ibatis.annotations.Param;

/**
 * @author lvlinc
 * @date Created in 2019/5/9 17:34
 * @description: 机修承接车辆
 * @modified By:lvlinc
 */
public interface CjclService extends IService<Cjcl> {
    /**
     * 查询列表分页信息
     * @param page
     * @param cjcl
     * @return
     */
    IPage<Cjcl> findCjclPage(IPage<Cjcl> page, Cjcl cjcl);

    /**
     * 统计承接车辆数量
     * @param cjcl
     * @return
     */
    Cjcl CountAll(Cjcl cjcl);
    /**
     * 获取车辆详情信息
     * @param ywlsh
     * @return
     */
    Cjcl getById(String ywlsh);
    /**
     * 获取车辆照片信息
     * @param id 主键ID
     * @return
     */
    Cjcl findImgById(@Param("id") String id);
}
