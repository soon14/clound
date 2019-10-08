package com.cloud.portal.archive.kyzy.service.impl;

import com.cloud.portal.archive.common.base.service.impl.DataServiceImpl;
import com.cloud.portal.archive.kyzy.mapper.SealInfoMapper;
import com.cloud.portal.archive.kyzy.model.SealInfo;
import com.cloud.portal.archive.kyzy.service.SealInfoService;
import org.springframework.stereotype.Service;

/**
 * @author hyx
 * @date Created in 2019/6/18 10:56
 * @description: 刻印章业-印章service层实现
 * @modified By:
 */
@Service
public class SealInfoServiceImpl extends DataServiceImpl<SealInfoMapper, SealInfo> implements SealInfoService {


    /**
     * 根据id获取刻印章
     * @param id
     * @return
     */
    @Override
    public SealInfo findById(String id) {
        return this.baseMapper.findById(id);
    }
}
