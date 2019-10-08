package com.cloud.portal.archive.mechanical.service.impl;

import com.cloud.portal.archive.common.base.service.impl.DataServiceImpl;
import com.cloud.portal.archive.mechanical.mapper.JxyxtMapper;
import com.cloud.portal.archive.mechanical.model.Jxyxt;
import com.cloud.portal.archive.mechanical.service.JxyxtService;
import org.springframework.stereotype.Service;
/**
 * @author lvlinc
 * @date Created in 2019/5/9 17:34
 * @description: 机修业企业信息
 * @modified By:lvlinc
 */
@Service
public class JxyxtServiceImpl extends DataServiceImpl<JxyxtMapper, Jxyxt> implements JxyxtService {

    @Override
    public Jxyxt getById(String zagldwbm) {
        return baseMapper.getById(zagldwbm);
    }
}
