package com.cloud.portal.point.detail.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.point.detail.model.IndexDetail;
import com.cloud.portal.point.detail.service.IndexDetailService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author wengshij
 * @date Created in 2019/7/16 14:19
 * @description: 积分指标明细（包含自动积分指标和手动积分指标）
 * @modified By:wengshij
 */
@RestController
@AllArgsConstructor
@RequestMapping("/point/detail/")
@Api(value = "detail")
public class IndexDetailController {

    private final IndexDetailService indexDetailService;

    /**
     * @param page
     * @param indexDetail
     * @return
     */
    @GetMapping("countPage")
    @SysLog("积分指标明细统计")
    public R countPage(Page page, IndexDetail indexDetail) {
        return R.ok(indexDetailService.countPage(page, indexDetail));
    }

    @GetMapping("detailPage")
    @SysLog("积分指标明细")
    public R detailPage(Page page, IndexDetail indexDetail) {
        return R.ok(indexDetailService.detailPage(page, indexDetail));
    }

    @PutMapping("passivePoint")
    @SysLog("手动积分")
    @PreAuthorize("@pms.hasPermission('point_passive_score')")
    public R passivePoint(@Valid @RequestBody IndexDetail indexDetail) {
        return R.ok(indexDetailService.passivePoint(indexDetail));
    }


}
