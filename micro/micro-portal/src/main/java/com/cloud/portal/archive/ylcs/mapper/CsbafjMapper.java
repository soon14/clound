package com.cloud.portal.archive.ylcs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.archive.ylcs.entity.Csbafj;
import org.apache.ibatis.annotations.Param;


/**
 * @author ryt
 * @date Created in 2019/5/22 19:05
 * @description: 娱乐场所-场所备案附件(证件信息)mapper层
 * @modified By:
 */
public interface CsbafjMapper extends BaseMapper<Csbafj> {

    /**
     * 查询场所备案附件列表
     *
     * @param page,csbafj
     * @return 场所备案附件列表
     */
    IPage<Csbafj> findCsbafjPage(IPage<Csbafj> page, @Param("query") Csbafj csbafj);

    /**
     * 获取场所 照片信息
     * @param csbafj 照片ID
     * @return
     */
    Csbafj findImgById(@Param("query") Csbafj csbafj);

    /**
     * 获取场所平面图
     * @param csbaId 场所ID
     * @return
     */
    Csbafj findPmtById(@Param("csbaId")String csbaId);
}
