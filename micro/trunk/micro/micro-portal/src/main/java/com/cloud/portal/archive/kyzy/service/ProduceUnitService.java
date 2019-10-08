package com.cloud.portal.archive.kyzy.service;

import com.cloud.portal.archive.common.base.service.DataService;
import com.cloud.portal.archive.kyzy.model.ProduceUnit;


/**
 * @author hyx
 * @date Created in 2019/6/18 9:34
 * @description: 刻印章业-单位service层
 * @modified By:
 */
public interface ProduceUnitService extends DataService<ProduceUnit> {

    /**
     * 根据id获取刻印章单位
     * @param id
     * @return
     */
    public ProduceUnit findById(String id);

}
