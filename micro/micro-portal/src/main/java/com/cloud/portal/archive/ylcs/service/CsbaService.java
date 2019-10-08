package com.cloud.portal.archive.ylcs.service;

import com.cloud.portal.archive.common.base.service.DataService;
import com.cloud.portal.archive.ylcs.entity.Csba;


/**
 * @author ryt
 * @date Created in 2019/5/9 17:34
 * @description: 娱乐场所-场所备案service层
 * @modified By:
 */
public interface CsbaService extends DataService<Csba> {

    /**
     * 通过ID查询单位的详细信息
     *
     * @param csbaId
     * @return 单位详细信息
     */
    Csba getById(String csbaId);

}
