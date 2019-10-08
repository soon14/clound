package com.cloud.portal.archive.mechanical.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.archive.mechanical.mapper.JxCyryMapper;
import com.cloud.portal.archive.mechanical.model.Cjcl;
import com.cloud.portal.archive.mechanical.model.Cyry;
import com.cloud.portal.archive.mechanical.service.JxCyryService;
import org.springframework.stereotype.Service;
/**
 * @author lvlinc
 * @date Created in 2019/5/9 17:34
 * @description: 机修从业人员
 * @modified By:lvlinc
 */
@Service
public class JxCyryServiceImpl extends ServiceImpl<JxCyryMapper, Cyry> implements JxCyryService {
    @Override
    public IPage<Cyry> findCyryPage(IPage<Cyry> page, Cyry cyry) {
        return baseMapper.findCyryPage(page,cyry);
    }

    @Override
    public Cyry CountAll(Cyry cyry) {
        return baseMapper.CountAll(cyry);
    }

    @Override
    public Cyry getById(String xxid) {
        return baseMapper.getById(xxid);
    }

    @Override
    public Cyry CyryImgById(String id) {
        return baseMapper.CyryImgById(id);
    }
}
