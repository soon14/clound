package com.cloud.portal.judged.warn.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.portal.judged.warn.model.WarnInfo;
import com.cloud.portal.judged.warn.service.WarnInfoService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wengshij
 * @date Created in 2019/6/25 9:51
 * @description: 异常警告信息 访问层
 * @modified By:wengshij
 */
@RestController
@AllArgsConstructor
@RequestMapping("/judged/warn/info")
@Api(value = "warn")
public class WarnInfoController {

    private final WarnInfoService warnInfoService;

    /**
     * 异常信息 获取列表
     *
     * @param page
     * @param warnInfo
     * @return
     */
    @GetMapping("/page")
    public R getPage(Page page, WarnInfo warnInfo) {
        return R.ok(warnInfoService.page(page, Wrappers.query(warnInfo)));
    }

    /**
     * 异常信息 获取列表
     * 关联 模型信息 及其他积分管理警告信息
     *
     * @param page
     * @param warnInfo
     * @return
     */
    @GetMapping("/relation/page")
    public R relationPage(Page page, WarnInfo warnInfo) {
        return R.ok(warnInfoService.findPage(page, warnInfo));
    }


}
