package com.cloud.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.api.entity.SysParameter;
import com.cloud.admin.service.SysParameterService;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/parameter")
@Api(value = "parameter" ,description = "全局参数模块")
public class ParameterController {

    private final SysParameterService sysParameterService;

    /**
     * 根据id查询全局参数信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id){
        return new R<>(sysParameterService.getById(id));
    }

    /**
     * 分页查询参数列表
     * @param page
     * @param sysParameter
     * @return
     */
    @GetMapping("/page")
    public R<IPage> getParameterPage(Page page, SysParameter sysParameter){
        return new R<>(sysParameterService.page(page, Wrappers.query(sysParameter)));
    }

    /**
     * 根据键值获取参数
     * @param parameterKey
     * @return
     */
    @GetMapping("/key/{parameterKey}")
    @Cacheable(value = "parameter_details",key = "#parameterKey")
    public R getParameterByKey(@PathVariable String parameterKey){
        return new R<>(sysParameterService.list(Wrappers
                .<SysParameter>query().lambda()
                .eq(SysParameter::getParameterKey,parameterKey)));
    }

    /**
     * 添加参数
     * @param sysParameter
     * @return
     */
    @SysLog("添加参数")
    @PostMapping("/save")
    @PreAuthorize("@pms.hasPermission('sys_parameter_add')")
    public R save(@Valid @RequestBody SysParameter sysParameter){
        return new R<>(sysParameterService.saveParameter(sysParameter));
    }

    /**
     * 删除参数
     * @param id
     * @return
     */
    @SysLog("删除参数")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_parameter_del')")
    public R removeById(@PathVariable String id){
        SysParameter sysParameter =sysParameterService.getById(id);
        return new R<>(sysParameterService.deleteById(sysParameter));
    }

    @SysLog("更新参数")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys_parameter_edit')")
    public R updateById(@Valid @RequestBody SysParameter sysParameter){
        return new R<>(sysParameterService.updateParameter(sysParameter));
    }

}
