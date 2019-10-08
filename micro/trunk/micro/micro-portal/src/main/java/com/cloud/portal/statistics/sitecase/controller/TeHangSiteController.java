package com.cloud.portal.statistics.sitecase.controller;

import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.statistics.sitecase.model.TeHangSite;
import com.cloud.portal.statistics.sitecase.service.TeHangSiteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lvlinc
 * @date Created in 2019/8/7
 * @description:统计报表-特行场所基础情况统计
 * @modified By:lvlinc
 */
@RestController
@AllArgsConstructor
@RequestMapping("site/case")
public class TeHangSiteController {
    private final TeHangSiteService teHangSiteService;

    @GetMapping("/siteList")
    @SysLog("统计报表-特行场所基础情况统计")
    public R siteList(TeHangSite teHangSite) {
        return R.ok(teHangSiteService.siteList(teHangSite));
    }
}
