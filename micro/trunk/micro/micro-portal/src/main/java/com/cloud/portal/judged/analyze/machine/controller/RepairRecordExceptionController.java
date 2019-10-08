package com.cloud.portal.judged.analyze.machine.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.judged.analyze.machine.model.RepairRecordException;
import com.cloud.portal.judged.analyze.machine.service.RepairRecordExceptionService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wengshij
 * @date Created in 2019/7/2 14:46
 * @description: 机修 维修记录异常分析（按天统计-每天记录少于3次视为异常）
 * @modified By:wengshij
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/analyze/machine/rre")
@Api(value = "RepairRecordException")
public class RepairRecordExceptionController {

    private final RepairRecordExceptionService repairRecordExceptionService;

    @GetMapping(value = "/page")
    @SysLog("维修记录异常分析查询")
    @PreAuthorize("@pms.hasPermission('repair_record_exception')")
    public R index(Page page, RepairRecordException repairRecordException) {
        return R.ok(repairRecordExceptionService.analyzePage(page, repairRecordException));
    }

    @GetMapping(value = "/listPage")
    @SysLog("维修记录异常分析查询")
    @PreAuthorize("@pms.hasPermission('repair_record_exception')")
    public R list(Page page, RepairRecordException repairRecordException) {
        return R.ok(repairRecordExceptionService.findListPage(page, repairRecordException));
    }
}
