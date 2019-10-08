package com.cloud.portal.judged.analyze.entertainment.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.judged.analyze.entertainment.model.PersonTruthReport;
import com.cloud.portal.judged.analyze.entertainment.service.PersonTruthReportService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ryt
 * @date Created in 2019/7/9 09:46
 * @description: 从业人员未如实报备分析模型-针对娱乐场所
 * @modified By:
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/analyze/entertainment/personTruthReport")
@Api(value = "personTruthReport")
public class PersonTruthReportController {

    private final PersonTruthReportService personTruthReportService;

    @GetMapping(value = "/page")
    @SysLog("从业人员未如实报备分析查询")
    @PreAuthorize("@pms.hasPermission('person_truth_report')")
    public R index(Page page, PersonTruthReport personTruthReport) {
        return R.ok(personTruthReportService.analyzePage(page,personTruthReport));
    }

    @GetMapping(value = "/listPage")
    @SysLog("从业人员未如实报备分析 详情")
    @PreAuthorize("@pms.hasPermission('person_truth_report')")
    public R list(Page page,PersonTruthReport personTruthReport) {
        return R.ok(personTruthReportService.findListPage(page,personTruthReport));
    }
}
