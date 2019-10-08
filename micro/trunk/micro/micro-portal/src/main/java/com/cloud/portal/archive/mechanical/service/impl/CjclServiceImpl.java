package com.cloud.portal.archive.mechanical.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.archive.mechanical.mapper.CjclMapper;
import com.cloud.portal.archive.mechanical.model.Cjcl;
import com.cloud.portal.archive.mechanical.service.CjclService;
import org.springframework.stereotype.Service;
/**
 * @author lvlinc
 * @date Created in 2019/5/9 17:34
 * @description: 机修承接车辆
 * @modified By:lvlinc
 */
@Service
public class CjclServiceImpl extends ServiceImpl<CjclMapper, Cjcl> implements CjclService {
    @Override
    public IPage<Cjcl> findCjclPage(IPage<Cjcl> page, Cjcl cjcl) {
        return baseMapper.findCjclPage(page,cjcl);
    }

    @Override
    public Cjcl CountAll(Cjcl cjcl) {
        return baseMapper.CountAll(cjcl);
    }

    @Override
    public Cjcl getById(String ywlsh) {
        return baseMapper.getById(ywlsh);
    }

    @Override
    public Cjcl findImgById(String id) {

        return baseMapper.findImgById(id);
    }
}
