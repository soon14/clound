package com.cloud.portal.archive.contact.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.archive.contact.entity.Csgl;
import com.cloud.portal.archive.contact.service.CsglService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author ryt
 * @date Created in 2019/6/4 14:51
 * @description: 场所关联controller层
 * @modified By:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ylcs/contact")
@Api(value = "contact" ,description = "智能档案场所关联模块")
public class CsglController {

    private final CsglService csglService;

    /**
     * 分页查询场所关联列表
     * @param page
     * @param csgl
     * @return
     */
    @GetMapping("/glPage")
    public R<IPage> getCsglPage(Page page, Csgl csgl){
        return new R<>(csglService.findContactPage(page, csgl));
    }

    /**
     * 分页查询场所列表
     * @param page
     * @param csgl
     * @return
     */
    @GetMapping("/page")
    public R<IPage> getPage(Page page, Csgl csgl){
        return new R<>(csglService.findPage(page, csgl));
    }

    /**
     * 添加
     *
     * @param csgl
     * @return success/false
     */
    @SysLog("添加关联")
    @PostMapping
    public R save(@Valid @RequestBody Csgl csgl) {
        return new R<>(csglService.save(csgl));
    }

}
