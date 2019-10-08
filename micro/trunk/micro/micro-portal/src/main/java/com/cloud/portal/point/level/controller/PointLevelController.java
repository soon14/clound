package com.cloud.portal.point.level.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.point.level.model.PointLevel;
import com.cloud.portal.point.level.service.PointLevelService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @author ryt
 * @date Created in 2019/6/21 10:15
 * @description: 等级评定方案控制层
 * @modified By:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/point/level")
@Api(value = "pointLevel", description = "积分管理-等级评定方案管理模块")
public class PointLevelController {

    private final PointLevelService pointLevelService;

    /**
     * 等级评定方案分页查询
     *
     * @param page 分页对象
     * @return 分页等级评定方案数据
     */
    @GetMapping("/page")
    @SysLog("等级评定方案查询")
    @PreAuthorize("@pms.hasPermission('point_level_list')")
    public R page(Page page, PointLevel pointLevel) {
        return new R<>(pointLevelService.findPage(page, pointLevel));
    }

    /**
     * 等级评定子项查询
     *
     * @param levelId 方案id
     * @return 等级评定子项
     */
    @GetMapping("/child/{levelId}")
    @SysLog("等级评定子项查询")
    public R findChildList(@PathVariable String levelId) {
        return new R<>(pointLevelService.findChildList(levelId));
    }

    /**
     * 通过ID查询等级评定方案
     *
     * @param id ID
     * @return 等级评定方案
     */
    @GetMapping("/get/{id}")
    public R getById(@PathVariable String id) {
        return new R<>(pointLevelService.getById(id));
    }

    /**
     * 新增等级评定方案
     *
     * @param pointLevel
     * @return
     */
    @SysLog("新增等级评定方案")
    @PostMapping("/save")
    @PreAuthorize("@pms.hasPermission('point_level_add')")
    public R save(@Valid @RequestBody PointLevel pointLevel) {
        return pointLevelService.savePointLevel(pointLevel);
    }

    /**
     * 更新等级评定方案
     *
     * @param pointLevel
     * @return
     */
    @SysLog("更新等级评定方案")
    @PutMapping("/update")
    @PreAuthorize("@pms.hasPermission('point_level_edit')")
    public R updateById(@Valid @RequestBody PointLevel pointLevel) {
        return pointLevelService.updatePointLevel(pointLevel);
    }

    /**
     * 删除等级评定方案
     *
     * @param id
     * @return
     */
    @SysLog("删除等级评定方案")
    @DeleteMapping("/del/{id}")
    @PreAuthorize("@pms.hasPermission('point_level_del')")
    public R removeById(@PathVariable String id) {
        return pointLevelService.removePointLevel(id);
    }

    /**
     * 等级评定方案状态更新
     * @param id 方案id
     * @param status 状态（启用、停用）
     * @return
     */
    @SysLog("等级评定方案状态更新")
    @PutMapping("/status")
    @PreAuthorize("@pms.hasPermission('point_level_edit')")
    public R changeStatus(String id, String status) {
        return pointLevelService.updateStatus(id, status);
    }

}
