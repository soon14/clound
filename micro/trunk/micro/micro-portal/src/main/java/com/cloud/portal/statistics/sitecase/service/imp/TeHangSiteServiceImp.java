package com.cloud.portal.statistics.sitecase.service.imp;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.vo.TreeUtil;
import com.cloud.portal.statistics.sitecase.mapper.TeHangSiteMapper;
import com.cloud.portal.statistics.sitecase.model.TeHangSite;
import com.cloud.portal.statistics.sitecase.service.TeHangSiteService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author lvlinc
 * @date Created in 2019/8/7
 * @description:统计报表-特行场所基础情况统计
 * @modified By:lvlinc
 */
@Service
public class TeHangSiteServiceImp extends ServiceImpl<TeHangSiteMapper, TeHangSite> implements TeHangSiteService {

    @Override
    public List<TeHangSite> siteList(TeHangSite teHangSite) {
        teHangSite.setEndTime(teHangSite.getEndTime().replace("-",""));
        teHangSite.setStartTime(teHangSite.getStartTime().replace("-",""));
        if(teHangSite!=null&& StringUtils.isNotBlank(teHangSite.getCityCode())){
            teHangSite.setCityCode(teHangSite.getCityCode().replaceAll("0*$", ""));
        }
        return TreeUtil.bulid(baseMapper.siteList(teHangSite), 0);
    }
}
