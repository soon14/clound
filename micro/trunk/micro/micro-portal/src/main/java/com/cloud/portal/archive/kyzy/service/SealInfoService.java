package com.cloud.portal.archive.kyzy.service;

import com.cloud.portal.archive.common.base.service.DataService;
import com.cloud.portal.archive.kyzy.model.SealInfo;


/**
 * @author hyx
 * @date Created in 2019/6/18 9:34
 * @description: 刻印章业-单位service层
 * @modified By:
 */
public interface SealInfoService extends DataService<SealInfo> {

    /**
     * 根据id获取印章
     * @param id
     * @return
     */
    public SealInfo findById(String id);

}
