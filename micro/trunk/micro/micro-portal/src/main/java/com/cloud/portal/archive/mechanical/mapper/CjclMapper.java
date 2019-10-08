package com.cloud.portal.archive.mechanical.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.archive.mechanical.model.Cjcl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * @author lvlinc
 * @date Created in 2019/5/9 17:34
 * @description: 机修承接车辆
 * @modified By:lvlinc
 */
@Mapper
public interface CjclMapper extends BaseMapper<Cjcl> {
    IPage<Cjcl> findCjclPage(IPage<Cjcl> page, @Param("query")Cjcl cjcl);
    /**
     * 查询列表分页信息
     * @param cjcl
     * @return
     */
    Cjcl CountAll(Cjcl cjcl);
    /**
     * 获取车辆详情信息
     * @param ywlsh
     * @return
     */
    Cjcl getById(@Param("ywlsh")String ywlsh);

    /**
     * 获取车辆照片信息
     * @param id 主键ID
     * @return
     */
    Cjcl findImgById(@Param("id") String id);
}
