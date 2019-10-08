package com.cloud.portal.archive.jingz.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.archive.jingz.model.Xfjc;


/**
 * @author ryt
 * @date Created in 2019/6/18 17:14
 * @description: 警综消防监督检查信息
 * @modified By:
 */
public interface XfjcService extends IService<Xfjc> {

    /**
     * 获取消防检查信息列表
     * @param page
     * @param xfjc
     * @return
     */
    IPage<Xfjc> findXfjcPage(IPage<Xfjc> page, Xfjc xfjc);

    /**
     * 通过ID查询检查详细信息
     *
     * @param systemId
     * @return 检查详细信息
     */
    Xfjc getXfjcById(String systemId);
}
