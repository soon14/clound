package com.cloud.portal.archive.kyzy.service.impl;

import com.cloud.portal.archive.common.base.service.impl.DataServiceImpl;
import com.cloud.portal.archive.kyzy.mapper.ProduceUnitMapper;
import com.cloud.portal.archive.kyzy.model.ProduceUnit;
import com.cloud.portal.archive.kyzy.service.ProduceUnitService;
import org.springframework.stereotype.Service;

/**
 * @author hyx
 * @date Created in 2019/6/18 10:56
 * @description: 刻印章业-单位service层实现
 * @modified By:
 */
@Service
public class ProduceUnitServiceImpl extends DataServiceImpl<ProduceUnitMapper, ProduceUnit> implements ProduceUnitService {


    /**
     * 根据id获取刻印章单位
     * @param id
     * @return
     */
    @Override
    public ProduceUnit findById(String id) {
        return this.baseMapper.findById(id);
    }
}
