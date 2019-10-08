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
     * 分页查询警综场所关联列表
     * @param page
     * @param csgl
     * @return
     */
    @GetMapping("/jzGlPage")
    public R<IPage> getJzCsglPage(Page page, Csgl csgl){
        return new R<>(csglService.findJzContactPage(page, csgl));
    }

    /**
     * 分页查询警综场所列表
     * @param page
     * @param csgl
     * @return
     */
    @GetMapping("/jzPage")
    public R<IPage> getJzPage(Page page, Csgl csgl){
        return new R<>(csglService.findJzPage(page, csgl));
    }

    /**
     * 添加关联
     *
     * @param csgl
     * @return success/false
     */
    @SysLog("添加关联")
    @PostMapping
    public R save(@Valid @RequestBody Csgl csgl) {
        return csglService.saveCsgl(csgl);
    }

    /**
     * 添加关联-警综
     *
     * @param csgl
     * @return success/false
     */
    @SysLog("添加关联-警综")
    @PostMapping("/jzContact")
    public R saveJz(@Valid @RequestBody Csgl csgl) {
        return csglService.saveJzCsgl(csgl);
    }

    /**
     * 自动添加关联
     *
     * @param
     * @return success/false
     */
    @SysLog("自动添加关联")
    @PostMapping("/autoContact")
    public R autoSave() {
        return new R<>(csglService.autoSave());
    }

    /**
     * 取消关联
     *
     * @param jzdwbh,qtdwbh 警综id，场所id
     * @return success/false
     */
    @SysLog("取消关联")
    @DeleteMapping("/{jzdwbh}/{qtdwbh}")
    public R removeById(@PathVariable String jzdwbh,@PathVariable String qtdwbh) {
        return new R<>(csglService.deleteCsgl(jzdwbh,qtdwbh));
    }

}
