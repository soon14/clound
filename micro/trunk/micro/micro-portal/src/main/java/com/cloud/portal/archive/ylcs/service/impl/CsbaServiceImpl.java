package com.cloud.portal.archive.ylcs.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.archive.common.base.service.impl.DataServiceImpl;
import com.cloud.portal.archive.ylcs.entity.Csba;
import com.cloud.portal.archive.ylcs.entity.Ryba;
import com.cloud.portal.archive.ylcs.mapper.CsbaMapper;
import com.cloud.portal.archive.ylcs.service.CsbaService;
import org.springframework.stereotype.Service;

/**
 * @author ryt
 * @date Created in 2019/5/9 18:56
 * @description: 娱乐场所-场所备案service层实现
 * @modified By:
 */
@Service
public class CsbaServiceImpl extends DataServiceImpl<CsbaMapper, Csba> implements CsbaService {

    @Override
    public Csba getById(String csbaId){
        return this.baseMapper.getById(csbaId);
    }

}
