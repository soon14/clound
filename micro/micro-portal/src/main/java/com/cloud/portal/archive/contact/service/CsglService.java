package com.cloud.portal.archive.contact.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.archive.contact.entity.Csgl;
import org.apache.ibatis.annotations.Param;

/**
 * @author ryt
 * @date Created in 2019/6/3 16:40
 * @description: 场所关联信息service层
 * @modified By:
 */
public interface CsglService extends IService<Csgl> {

    /**
     * 分页查询场所关联列表
     * @param page
     * @param csgl
     * @return
     */
    IPage<Csgl> findContactPage(IPage<Csgl> page,Csgl csgl);

    /**
     * 分页查询场所列表
     * @param page
     * @param csgl
     * @return
     */
    IPage<Csgl> findPage(IPage<Csgl> page, Csgl csgl);
}
