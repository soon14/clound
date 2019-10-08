package com.cloud.portal.statistics.sitecase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.statistics.sitecase.model.TeHangSite;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019/8/7
 * @description:统计报表-特行场所基础情况统计
 * @modified By:lvlinc
 */

public interface TeHangSiteService extends IService<TeHangSite> {
    /**
     * 查询数据表字段
     * @param teHangSite 查询参数列表
     * @return List<TeHangSite>
     */
    List<TeHangSite> siteList (TeHangSite teHangSite);
}
