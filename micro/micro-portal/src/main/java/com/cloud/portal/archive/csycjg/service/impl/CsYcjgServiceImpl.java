package com.cloud.portal.archive.csycjg.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.portal.archive.common.base.service.impl.DataServiceImpl;
import com.cloud.portal.archive.csycjg.mapper.CsYcjgMapper;
import com.cloud.portal.archive.csycjg.model.CsYcjg;
import com.cloud.portal.archive.csycjg.service.CsYcjgService;
import org.springframework.stereotype.Service;

/**
 * @author lvlinc
 * @date Created in 2019/6/5
 * @description:
 * @modified By:lvlinc
 */
@Service
public class CsYcjgServiceImpl extends DataServiceImpl<CsYcjgMapper, CsYcjg> implements CsYcjgService {
    @Override
    public IPage<CsYcjg> findPage(Page<CsYcjg> page, CsYcjg csYcjg) {
        return baseMapper.findPage(page,csYcjg);
    }
}
