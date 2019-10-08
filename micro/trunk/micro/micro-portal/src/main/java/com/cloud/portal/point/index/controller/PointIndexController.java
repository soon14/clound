package com.cloud.portal.point.index.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.point.index.model.PointIndex;
import com.cloud.portal.point.index.service.PointIndexService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author wengshij
 * @date Created in 2019/6/20 10:15
 * @description: 积分指标控制层
 * @modified By:wengshij
 */
@RestController
@AllArgsConstructor
@RequestMapping("/point/index/")
@Api(value = "point")
public class PointIndexController {

    private final PointIndexService pointIndexService;

    /**
     * 积分指标分页查询
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    @SysLog("积分指标查询")
    @PreAuthorize("@pms.hasPermission('point_index_list')")
    public R page(Page page, PointIndex pointIndex) {
        return R.ok(pointIndexService.findPage(page, pointIndex));
    }

    /**
     * 添加指标信息
     *
     * @param pointIndex
     * @return
     */
    @SysLog("添加指标")
    @PostMapping("save")
    @PreAuthorize("@pms.hasPermission('point_index_add')")
    public R save(@Valid @RequestBody PointIndex pointIndex) {
        return pointIndexService.saveIndex(pointIndex);
    }

    /**
     * 更新指标
     *
     * @param pointIndex
     * @return
     */
    @SysLog("更新指标")
    @PutMapping("update")
    @PreAuthorize("@pms.hasPermission('point_index_edit')")
    public R updateById(@Valid @RequestBody PointIndex pointIndex) {
        return pointIndexService.updateIndex(pointIndex);
    }

    /**
     * 删除指标
     *
     * @param id
     * @return
     */
    @SysLog("删除指标")
    @DeleteMapping("del/{id}")
    @PreAuthorize("@pms.hasPermission('point_index_del')")
    public R removeById(@PathVariable String id) {
        return pointIndexService.removeById(id);
    }

    /**
     * 通过ID查询角色信息
     *
     * @param id ID
     * @return 角色信息
     */
    @GetMapping("get/{id}")
    public R getById(@PathVariable String id) {
        return R.ok(pointIndexService.getById(id));
    }

    /**
     * 判断唯一标识符是否存在
     *
     * @param id
     * @param udId
     * @return
     */
    @GetMapping("udId/exit")
    public R udIdExit(@Param("id") String id, @Param("udId") String udId) {
        return R.ok(pointIndexService.udIdExit(id, udId));
    }

    @SysLog("指标更新状态")
    @PutMapping("status")
    @PreAuthorize("@pms.hasPermission('point_index_edit')")
    public R status(String id, String status) {
        return pointIndexService.updateStatus(id, status);
    }

    /**
     * * 获取指标信息、选择指标的时候按级选取
     * * 查询条件只是指标类型（默认为手动指标）
     *
     * @param indexType 指标类型
     * @param placeType 场所类型
     * @return
     */
    @GetMapping("indexTreeByType")
    public R indexTreeByType(String indexType, String placeType) {
        return R.ok(pointIndexService.getIndexTree(indexType, placeType));
    }



}
