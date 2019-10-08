package com.cloud.portal.point.scheme.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.point.scheme.model.PointScheme;
import com.cloud.portal.point.scheme.service.PointSchemeService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author lvlinc
 * @date Created in 2019/7/15
 * @description:积分方案管理控制层
 * @modified By:lvlinc
 */
@AllArgsConstructor
@RestController
@RequestMapping("/point/scheme")
@Api(value = "point")
public class PointSchemeController {
    private final PointSchemeService pointSchemeService;
    /**
     * 分页查询
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    @SysLog("积分方案查询")
    @PreAuthorize("@pms.hasPermission('point_scheme_list')")
    public R page(Page page, PointScheme pointScheme) {
        return new R<>(pointSchemeService.findPage(page,pointScheme));

    }

    /**
     * 积分周期详情查询
     * @param id
     * @return R
     */
    @GetMapping("/cycleList/{id}")
    @SysLog("积分周期详情查询")
    public R cycleList(@PathVariable String id) {
        return new R<>(pointSchemeService.cycleList(id));

    }

    /**
     * 获取方案运行周期
     * @param pointScheme
     * @return R
     */
    @GetMapping("/starCycle")
    @SysLog("获取方案运行周期")
    public R starCycle(PointScheme pointScheme) {
        R r = null;
        if (pointScheme.getCityCode()!=null && pointScheme.getPlaceType()!=null){
           r = new R<>(pointSchemeService.areaStarCycle(pointScheme));
            if (pointScheme.getId()==null){
                r = new R<>(pointSchemeService.starCycle());
            }
        }else {
            r = new R<>(pointSchemeService.starCycle());
        }
        return r;
    }

    /**
     * 添加信息
     * @param pointScheme
     * @return
     */
    @SysLog("积分方案添加")
    @PostMapping("/save")
    @PreAuthorize("@pms.hasPermission('point_scheme_add')")
    public R save(@Valid @RequestBody PointScheme pointScheme) {
        return new R<>(pointSchemeService.saveScheme(pointScheme));
    }

    /**
     * 更新
     * @param pointScheme
     * @return
     */
    @SysLog("积分方案更新")
    @PutMapping("/update")
    @PreAuthorize("@pms.hasPermission('point_scheme_edit')")
    public R updateById(@Valid @RequestBody PointScheme pointScheme) {
        return new R<>(pointSchemeService.updateScheme(pointScheme));
    }
    /**
     * 积分方案更新状态
     *
     * @param id,status
     * @return R
     */
    @SysLog("积分方案更新状态")
    @PutMapping("/status")
    @PreAuthorize("@pms.hasPermission('point_index_edit')")
    public R status(String id, String status) {
        return pointSchemeService.updateStatus(id, status);
    }

    /**
     * 重置积分
     * @param
     * @return
     */
    @SysLog("重置积分")
    @PutMapping("/resetScore")
    public R resetScore() {
        return new R<>(pointSchemeService.resetScore());
    }

    /**
     * 删除积分
     * @param id
     * @return
     */
    @SysLog("重置积分")
    @PutMapping("/deleteScore/{id}")
    public R deleteScore(@PathVariable String id) {
        return new R<>(pointSchemeService.deleteScore(id));
    }


    /**
     * 删除积分方案
     *
     * @param id
     * @return
     */
    @SysLog("删除积分方案")
    @DeleteMapping("/del/{id}")
    @PreAuthorize("@pms.hasPermission('point_scheme_del')")
    public R removeById(@PathVariable String id) {
        return new R<>(pointSchemeService.removePointScheme(id));
    }
}
