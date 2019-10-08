package com.cloud.portal.asj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.asj.model.DanweiAsj;

/**
 * @author lvlinc
 * @date Created in 2019/8/2210:36
 * @description:
 * @modified By:lvlinc
 */
public interface DanweiAsjService extends IService<DanweiAsj> {
    /**
     * fetch data by rule id
     *
     * @param page page number
     * @param danweiAsj json format context
     * @return IPage<DanweiAsj>
     */
    IPage<DanweiAsj> findPage (IPage<DanweiAsj> page, DanweiAsj danweiAsj);
}
