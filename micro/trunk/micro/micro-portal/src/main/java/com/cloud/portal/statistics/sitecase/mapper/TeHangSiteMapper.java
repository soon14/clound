package com.cloud.portal.statistics.sitecase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.portal.statistics.sitecase.model.TeHangSite;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019/8/7
 * @description:统计报表-特行场所基础情况统计
 * @modified By:lvlinc
 */
@Mapper
public interface TeHangSiteMapper extends BaseMapper<TeHangSite> {
            /**
             * 查询数据表字段
             * @param teHangSite 查询参数列表
             * @return List<TeHangSite>
             */
        List<TeHangSite> siteList (TeHangSite teHangSite);
}
