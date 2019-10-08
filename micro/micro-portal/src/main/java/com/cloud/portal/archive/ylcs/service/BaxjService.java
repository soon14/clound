package com.cloud.portal.archive.ylcs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.archive.ylcs.entity.Baxj;


/**
 * @author ryt
 * @date Created in 2019/5/20 10:00
 * @description: 娱乐场所-保安巡检service层
 * @modified By:
 */
public interface BaxjService extends IService<Baxj> {

    /**
     * 获取保安巡检信息列表
     *
     * @param page,ryLog
     * @return 保安巡检信息
     */
    IPage<Baxj> findBaxjPage(IPage<Baxj> page, Baxj baxj);

}
