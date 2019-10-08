package com.cloud.portal.area.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.admin.api.vo.TreeUtil;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.area.entity.Area;
import com.cloud.portal.area.entity.AreaTree;
import com.cloud.portal.area.service.AreaService;
import com.cloud.portal.common.constant.PortalConstants;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author ryt
 * @date Created in 2019/5/27 14:41
 * @description: 区域controller
 * @modified By: wengshij
 */
@RestController
@AllArgsConstructor
@RequestMapping("/area")
@Api(value = "area", description = "区域管理模块")
public class AreaController {

    private final AreaService areaService;

    /**
     * 返回树形行政区划集合
     *
     * @return 树形行政区划
     */
    @GetMapping(value = "/tree")
    @SysLog("区域查询")
    public R getTree(Area area) {

        List<Area> areaList;
        if (StringUtils.isBlank(area.getName()) && StringUtils.isBlank(area.getType())) {
            areaList = areaService.findAll(PortalConstants.ALL_AREA_CACHE_KEY);
        } else {
            areaList = areaService.list(Wrappers.query(area))
                    .stream().sorted(Comparator.comparingInt(Area::getSort))
                    .collect(Collectors.toList());
        }
        List<AreaTree> areaTreeList=initTree(areaList);
        return new R<>(TreeUtil.bulid(areaTreeList, "0"));
    }

    /**
     * 通过ID查询行政区划详细信息
     *
     * @param id 区域ID
     * @return 区域详细信息
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable String id) {
        return new R<>(areaService.getById(id));
    }

    /**
     * 新增行政区划
     *
     * @param area 行政区划信息
     * @return success/false
     */
    @SysLog("新增行政区划")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('area_add')")
    public R save(@Valid @RequestBody Area area) {
        return areaService.saveArea(area);
    }

    /**
     * 更新行政区划
     *
     * @param area
     * @return
     */
    @SysLog("更新行政区划")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('area_edit')")
    public R update(@Valid @RequestBody Area area) {
        return areaService.updateAreaById(area);
    }

    /**
     * 删除行政区划
     *
     * @param id 行政区划ID
     * @return success/false
     */
    @SysLog("删除行政区划")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('area_del')")
    public R removeById(@PathVariable String id) {
        return areaService.removeAreaById(id);
    }

    /**
     * 返回当前用户所属区域范围集合
     *
     * @return
     */
    @GetMapping(value = "/areaData")
    public R getUserArea(Area area) {

        List<Area> areaList;
        if (SecurityUtils.getUser().isAdmin()) {
            areaList = this.areaService.findAll(PortalConstants.ALL_AREA_CACHE_KEY);
        } else {
            areaList = areaService.getAreaList(area);
        }
        List<AreaTree> areaTreeList=initTree(areaList);
        if (StringUtils.isNotBlank(area.getFlag())) {
            return new R<>(TreeUtil.bulid(areaTreeList, "1"));
        }
        return new R<>(TreeUtil.bulid(areaTreeList, "0"));
    }

    /**
     * 根据名称查询区域信息
     *
     * @param name
     * @return
     */
    @GetMapping("/checkName/{name}")
    public R checkName(@PathVariable String name) {
        Area condition = new Area();
        condition.setName(name);
        return new R<>(areaService.getOne(new QueryWrapper<>(condition)));
    }

    @GetMapping("/checkCode/{code}")
    public R checkCode(@PathVariable String code) {
        Area condition = new Area();
        condition.setCode(code);
        return new R<>(areaService.getOne(new QueryWrapper<>(condition)));
    }

    /**
     * 获取所有行政区划信息（字典翻译适用）
     *
     * @return
     */
    @GetMapping("/dict/all")
    public List<Map> getAllArea() {
        return this.areaService.findAll(PortalConstants.ALL_AREA_CACHE_KEY).stream().map(area1 -> {
            Map map = new HashMap(1);
            map.put("label", area1.getFullName());
            map.put("value", area1.getCode());
            map.put("id", area1.getId());
            return map;
        }).collect(Collectors.toList());
    }


    /**
     * 获取区域数 可分级获取
     * 例如获取前两级（省厅、地市）
     *
     * @return
     */
    @GetMapping("/level/tree")
    public R findAreaLevel(Area area) {
        List<Area> list=this.areaService.findListLevel(area);
        return new R<>(TreeUtil.bulid(initTree(list), "0"));

    }


    /**
     * 初始化树形菜单
     * @param areaList
     * @return
     */
    private   List<AreaTree> initTree(List<Area> areaList){
        return areaList.stream().map(areas -> {
            AreaTree  treeNode = new AreaTree();
            treeNode.setId(areas.getId());
            treeNode.setCode(areas.getCode());
            treeNode.setName(areas.getName());
            treeNode.setFullName(areas.getFullName());
            treeNode.setParentId(areas.getParentId());
            treeNode.setParentIds(areas.getParentIds());
            treeNode.setType(areas.getType());
            treeNode.setRemarks(areas.getRemarks());
            return treeNode;
        }).collect(Collectors.toList());
    }

}
