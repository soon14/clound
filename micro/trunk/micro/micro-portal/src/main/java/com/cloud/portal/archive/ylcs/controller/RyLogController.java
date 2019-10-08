package com.cloud.portal.archive.ylcs.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.portal.archive.ylcs.entity.RyLog;
import com.cloud.portal.archive.ylcs.service.RyLogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ryt
 * @date Created in 2019/5/17 16:56
 * @description: 娱乐场所-人员日志（人员考勤）控制层
 * @modified By:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ylcs/ryLog")
public class RyLogController {

    private final RyLogService ryLogService;

    /**
     * 分页查询人员考勤信息列表
     * @param page
     * @param ryLog
     * @return
     */
    @GetMapping("/findRyLogPage")
    public R<IPage> getRyLogPage(Page page, RyLog ryLog){
        return new R<>(ryLogService.findRyLogPage(page,ryLog));
    }


    /**
     * 通过ID查询人员的详细信息
     *
     * @param rylogId
     * @return 人员详细信息
     */
    @GetMapping("/{rylogId}")
    public R getById(@PathVariable String rylogId) {
        return new R<>(ryLogService.getById(rylogId));
    }
}
