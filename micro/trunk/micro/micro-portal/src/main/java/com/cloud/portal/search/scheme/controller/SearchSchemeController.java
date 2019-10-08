package com.cloud.portal.search.scheme.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.search.scheme.model.SearchScheme;
import com.cloud.portal.search.scheme.service.SearchSchemeService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author ryt
 * @date Created in 2019/8/5 11:20
 * @description: 综合查询-查询方案配置controller
 * @modified By:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/search/scheme")
@Api(value = "searchScheme", description = "综合查询-查询方案配置")
public class SearchSchemeController {

    private final SearchSchemeService searchSchemeService;

    /**
     * 综合查询-查询方案配置 查询
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    @SysLog("综合查询-查询方案配置 查询")
    @PreAuthorize("@pms.hasPermission('search_scheme_list')")
    public R page(Page page, SearchScheme searchScheme) {
        return R.ok(searchSchemeService.page(page, Wrappers.query(searchScheme)));
    }

    /**
     * 查询方案配置新增
     *
     * @param searchScheme
     * @return
     */
    @SysLog("查询方案配置新增")
    @PostMapping("/save")
    @PreAuthorize("@pms.hasPermission('search_scheme_add')")
    public R save(@Valid @RequestBody SearchScheme searchScheme) {
        return searchSchemeService.saveScheme(searchScheme);
    }

    /**
     * 查询方案配置修改
     *
     * @param searchScheme
     * @return
     */
    @SysLog("查询方案配置修改")
    @PutMapping("/update")
    @PreAuthorize("@pms.hasPermission('search_scheme_edit')")
    public R updateById(@Valid @RequestBody SearchScheme searchScheme) {
        return searchSchemeService.updateScheme(searchScheme);
    }

    /**
     * 删除指标
     *
     * @param id
     * @return
     */
    @SysLog("删除指标")
    @DeleteMapping("/del/{id}")
    @PreAuthorize("@pms.hasPermission('search_scheme_del')")
    public R removeById(@PathVariable String id) {
        return searchSchemeService.removeById(id);
    }

    /**
     * 通过ID查询配置方案信息
     *
     * @param id ID
     * @return 配置方案信息
     */
    @GetMapping("/get/{id}")
    public R getById(@PathVariable String id) {
        return R.ok(searchSchemeService.getById(id));
    }

    @SysLog("配置方案状态更新")
    @PutMapping("/status")
    @PreAuthorize("@pms.hasPermission('search_scheme_edit')")
    public R status(String id, String status) {
        return searchSchemeService.updateStatus(id, status);
    }

    /**
     * 查询配置方案树
     *
     * @param
     * @return 配置方案树
     */
    @GetMapping("/tree")
    public R searchSchemeTree(){
        return R.ok(searchSchemeService.getSchemeTree());
    }

}
