package com.cloud.portal.asj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.asj.model.DanweiAsj;
import com.cloud.portal.asj.service.DanweiAsjService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lvlinc
 * @date Created in 2019/8/22
 * @description:警综案件单位信息
 * @modified By:lvlinc
 */
@RestController
@AllArgsConstructor
@RequestMapping("asj")
public class DanweiAsjController {
    private final DanweiAsjService danweiAsjService;

    @GetMapping("/dwAjxx")
    @SysLog("移动警务-特行案件查询")
    public R findPage(Page page, DanweiAsj danweiAsj){
        return new R<>(danweiAsjService.findPage(page,danweiAsj));
    }
}
