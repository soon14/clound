package com.cloud.portal.archive.contact.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.archive.contact.entity.Csgl;
import com.cloud.portal.archive.contact.mapper.CsglMapper;
import com.cloud.portal.archive.contact.service.CsglService;
import feign.Param;
import org.springframework.stereotype.Service;

/**
 * @author ryt
 * @date Created in 2019/6/3 16:41
 * @description: 场所关联信息service实现
 * @modified By:
 */
@Service
public class CsglServiceImpl extends ServiceImpl<CsglMapper, Csgl> implements CsglService {

    /**
     * 分页查询场所关联列表
     * @param page
     * @param csgl
     * @return
     */
    @Override
    public IPage<Csgl> findContactPage(IPage<Csgl> page, Csgl csgl){
        return this.baseMapper.findContactPage(page,csgl);
    }

    /**
     * 分页查询场所列表
     * @param page
     * @param csgl
     * @return
     */
    @Override
    public IPage<Csgl> findPage(IPage<Csgl> page, Csgl csgl){
        return this.baseMapper.findPage(page,csgl);
    }
}
