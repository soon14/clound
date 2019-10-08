package com.cloud.portal.archive.csgz.controller;

import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.common.security.service.MicroUser;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.archive.csgz.model.Csgz;
import com.cloud.portal.archive.csgz.service.CsgzService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author lvlinc
 * @date Created in 2019/5/22 10:22:00
 * @description: 特行场所关注信息
 * @modified By:lvlinc
 */
@RestController
@AllArgsConstructor
@RequestMapping("/Csgz")
public class CsgzController {
    private final CsgzService csgzService;
    /**
     * 场所是否关注
     * @param csgz
     * @return R
     */
    @SysLog("是否关注")
    @GetMapping("/isgz")
    public R isgz( Csgz csgz){
        MicroUser user = SecurityUtils.getUser();
        csgz.setUserid(user.getId());
        csgz.setCsdwbh(csgz.getDwbh());
        return new R<>(csgzService.getis(csgz));
    }
}
