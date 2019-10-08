package com.cloud.portal.archive.csycjg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.archive.csycjg.model.CsYcjg;
import com.cloud.portal.archive.csycjg.service.CsYcjgService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lvlinc
 * @date Created in 2019/6/5
 * @description: 特行场所异常警告信息
 * @modified By:lvlinc
 */
@RestController
@AllArgsConstructor
@RequestMapping("/CsYcjg")
public class CsYcjgController {
    private final CsYcjgService csYcjgService;

     /**
     * 分页查询数据表字段
     * @param page   分页参数集
     * @param csYcjg 查询参数列表
     * @return R 数据表集合
     */
        @GetMapping("/page")
        @SysLog("异常警告信息")
    public R findPage(CsYcjg csYcjg, Page page){

            return new R<>(csYcjgService.findPage(page,csYcjg));
        }
}
