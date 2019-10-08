package com.cloud.portal.archive.mechanical.service;

import com.cloud.portal.archive.common.base.service.DataService;
import com.cloud.portal.archive.mechanical.model.Jxyxt;
/**
 * @author lvlinc
 * @date Created in 2019/5/9 17:34
 * @description: 机修业企业信息
 * @modified By:lvlinc
 */
public interface JxyxtService extends DataService<Jxyxt> {
    /**
     * 通过ID查询单位的详细信息
     *
     * @param zagldwbm
     * @return 单位详细信息
     */
    Jxyxt getById(String zagldwbm);
}
