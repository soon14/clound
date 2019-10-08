package com.cloud.portal.judged.analyze.machine.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.judged.analyze.machine.model.DifferentPlacesRevamp;
import com.cloud.portal.judged.analyze.machine.service.DifferentPlacesRevampService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lvlinc
 * @date Created in 2019/7/10
 * @description: 机修 同一车辆不同车行多次修改分析 (一个月内同一车辆不同车行修改3次以上)
 * @modified By:lvlinc
 */
@RestController
@AllArgsConstructor
@RequestMapping("/different/places/revamp")
public class DifferentPlacesRevampController {
    private final DifferentPlacesRevampService differentPlacesRevampService;

    @GetMapping(value = "/listPage")
    @SysLog("同一车辆不同车行多次修改分析列表")
    @PreAuthorize("@pms.hasPermission('same_site_multiple_modification')")
    public R list(Page page, DifferentPlacesRevamp dpr) {
        return R.ok(differentPlacesRevampService.findPage(page, dpr));
    }

    @GetMapping(value = "/monthPage")
    @SysLog("同一车辆不同车行多次修改分析")
    @PreAuthorize("@pms.hasPermission('same_site_multiple_modification')")
    public R monthPage(Page page, DifferentPlacesRevamp dpr) {
        return R.ok(differentPlacesRevampService.monthPage(page, dpr));
    }

    @GetMapping(value = "/oneToMany")
    @SysLog("同一车辆不同车行多次修改分析详情页")
    @PreAuthorize("@pms.hasPermission('same_site_multiple_modification')")
    public R oneToMany(Page page, DifferentPlacesRevamp dpr) {
        return R.ok(differentPlacesRevampService.oneToMany(page, dpr));
    }
}
