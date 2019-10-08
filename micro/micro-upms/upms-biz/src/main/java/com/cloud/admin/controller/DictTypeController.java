package com.cloud.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.api.entity.SysDictType;
import com.cloud.admin.service.SysDictTypeService;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.common.security.util.SecurityUtils;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * @author wuxx
 * @date 2019/2/19 16:55
 * @description: 字典类型controller
 * @modified By:
 **/

@RestController
@AllArgsConstructor
@RequestMapping("/dicttype")
@Api(value = "dict", description = "字典管理模块")
public class DictTypeController {

    private final SysDictTypeService sysDictTypeService;

    /**
     * 分页查询字典类型信息
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    public R<IPage> getDictPage(Page page, SysDictType sysDictType) {
        return new R<>(sysDictTypeService.page(page, Wrappers.query(sysDictType)));
    }

    /**
     * 添加字典类型
     *
     * @param sysDictType 字典类型信息
     * @return success、false
     */
    @SysLog("添加字典")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys_dict_add')")
    public R save(@Valid @RequestBody SysDictType sysDictType) {
        SysDictType condition = new SysDictType();
        condition.setInnerFlag(sysDictType.getInnerFlag());
        int num = sysDictTypeService.count(new QueryWrapper<>(condition));
        if(num > 0){
            return new R<>(new RuntimeException("该字典类型已经被使用"));
        }
        sysDictType.setCreateBy(SecurityUtils.getUser().getId());
        return new R<>(sysDictTypeService.save(sysDictType));
    }

    /**
     * 删除字典字典类型，并且清除字典缓存
     *
     * @param id   ID
     * @return R
     */
    @SysLog("删除字典类型")
    @DeleteMapping("/{id}/{innerFlag}")
    @PreAuthorize("@pms.hasPermission('sys_dict_del')")
    public R removeById(@PathVariable String id, @PathVariable String innerFlag) {
        return new R<>(sysDictTypeService.removeDictType(id, innerFlag));
    }

    /**
     * 修改字典
     *
     * @param sysDictType 字典信息
     * @return success/false
     */
    @PutMapping
    @SysLog("修改字典")
    @PreAuthorize("@pms.hasPermission('sys_dict_edit')")
    public R updateById(@Valid @RequestBody SysDictType sysDictType) {
        SysDictType condition = new SysDictType();
        condition.setInnerFlag(sysDictType.getInnerFlag());
        SysDictType existDictType = sysDictTypeService.getOne(new QueryWrapper<>(condition));

        if(existDictType != null && !existDictType.getId().equals(sysDictType.getId())){
            //新的字典类型 与 其他某个字典的字典类型相同时 提示该字典类型已经被使用
            return new R<>(new RuntimeException("该字典类型已经被使用"));
        }
        sysDictType.setUpdateBy(SecurityUtils.getUser().getId());
        sysDictType.setUpdateTime(LocalDateTime.now());
        return new R<>(sysDictTypeService.updateById(sysDictType));
    }
    
}
