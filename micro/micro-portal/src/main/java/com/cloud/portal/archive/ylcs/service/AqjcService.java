package com.cloud.portal.archive.ylcs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.archive.ylcs.entity.Aqjc;
import com.cloud.portal.archive.ylcs.entity.AqjcContent;

import java.util.List;


/**
 * @author ryt
 * @date Created in 2019/5/20 15:00
 * @description: 娱乐场所-公安日常检查service层
 * @modified By:
 */
public interface AqjcService extends IService<Aqjc> {

    /**
     * 获取安全检查信息列表
     * @param page
     * @param aqjc
     * @return
     */
    IPage<Aqjc> findAqjcPage(IPage<Aqjc> page, Aqjc aqjc);
    /**
     * 通过ID查询检查详细信息
     *
     * @param aqjcId
     * @return 检查详细信息
     */
    Aqjc getAqjcById( String aqjcId);

    /**
     * 获取安全检查项内容
     * @param aqjcId
     * @return
     */
    List<AqjcContent> getAqjcContent(String aqjcId);
}
