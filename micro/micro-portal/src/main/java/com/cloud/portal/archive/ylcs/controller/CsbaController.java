package com.cloud.portal.archive.ylcs.controller;

import com.cloud.common.core.util.R;
import com.cloud.portal.archive.ylcs.service.CsbaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ryt
 * @date Created in 2019/5/9 17:34
 * @description: 娱乐场所-场所备案控制层
 * @modified By:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ylcs/csba")
public class CsbaController {

    private final CsbaService csbaService;
    /**
     * 通过ID查询单位的详细信息
     *
     * @param csbaId 单位ID
     * @return 单位详细信息
     */
    @GetMapping("/{csbaId}")
    public R getById(@PathVariable String csbaId) {
        return new R<>(csbaService.getById(csbaId));
    }

}
