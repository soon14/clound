package com.cloud.portal.archive.contact.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.archive.contact.entity.Csgl;
import com.cloud.portal.archive.ylcs.entity.Aqjc;
import org.apache.ibatis.annotations.Param;

/**
 * @author ryt
 * @date Created in 2019/6/3 16:38
 * @description: 场所关联信息mapper
 * @modified By:
 */
public interface CsglMapper extends BaseMapper<Csgl> {

    /**
     * 分页查询场所关联列表
     * @param page
     * @param csgl
     * @return
     */
    IPage<Csgl> findContactPage(IPage<Csgl> page, @Param("query") Csgl csgl);

    /**
     * 分页查询场所关联列表
     * @param page
     * @param csgl
     * @return
     */
    IPage<Csgl> findPage(IPage<Csgl> page, @Param("query") Csgl csgl);
}
