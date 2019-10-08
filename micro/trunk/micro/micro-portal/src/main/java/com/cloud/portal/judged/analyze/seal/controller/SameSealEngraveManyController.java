package com.cloud.portal.judged.analyze.seal.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.judged.analyze.seal.model.SameSealEngraveMany;
import com.cloud.portal.judged.analyze.seal.service.SameSealEngraveManyService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wengshij
 * @date Created in 2019/7/9 15:18
 * @description: 同一单位印章被刻制多次分析
 * @modified By:wengshij
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/analyze/seal/sem")
@Api(value = "SameSealEngraveMany")
public class SameSealEngraveManyController {

    private final SameSealEngraveManyService sameSealEngraveManyService;

    @GetMapping(value = "/page")
    @SysLog("同一单位印章被刻制多次分析")
    @PreAuthorize("@pms.hasPermission('same_seal_engrave_many')")
    public R index(Page page, SameSealEngraveMany sameSealEngraveMany) {
        return R.ok(sameSealEngraveManyService.analyzePage(page, sameSealEngraveMany));
    }

    @GetMapping(value = "/listPage")
    @SysLog("同一单位印章被刻制多次列表轨迹")
    @PreAuthorize("@pms.hasPermission('same_seal_engrave_many')")
    public R list(Page page, SameSealEngraveMany sameSealEngraveMany) {
        return R.ok(sameSealEngraveManyService.findListPage(page, sameSealEngraveMany));
    }
}
