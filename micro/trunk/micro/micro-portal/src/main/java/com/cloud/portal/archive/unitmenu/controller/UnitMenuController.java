package com.cloud.portal.archive.unitmenu.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.admin.api.vo.TreeUtil;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.archive.unitmenu.entity.UnitMenu;
import com.cloud.portal.archive.unitmenu.entity.UnitMenuTree;
import com.cloud.portal.archive.unitmenu.service.UnitMenuService;
import com.cloud.portal.common.constant.PortalConstants;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ryt
 * @date Created in 2019/5/7 11:13
 * @description: 场所分类显示信息contoller
 * @modified By:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/unitMenu")
@Api(value = "unitMenu", description = "场所分类显示信息模块")
public class UnitMenuController {

  private final UnitMenuService unitMenuService;

    /**
     * 返回树形菜单集合
     *
     * @return 树形菜单
     */
    @GetMapping(value = "/tree")
    @SysLog("场所菜单查询")
    public R getTree(UnitMenu unitMenu){
      List<UnitMenuTree> unitMenuTreeList;
      List<UnitMenu> unitMenuList;
      if (StringUtils.isBlank(unitMenu.getName())) {
        unitMenuList = unitMenuService.findAll(PortalConstants.ALL_UNIT_MENU_CACHE_KEY);
      } else {
        unitMenuList = unitMenuService.list(Wrappers.query(new UnitMenu()).like(
                StringUtils.isNotBlank(unitMenu.getName()),UnitMenu::getName,unitMenu.getName()));
      }
      unitMenuTreeList = unitMenuList.stream().map(unitMenus -> {
        UnitMenuTree node = new UnitMenuTree();
        node.setId(unitMenus.getId());
        node.setName(unitMenus.getName());
        node.setParentId(unitMenus.getParentId());
        node.setParentIds(unitMenus.getParentIds());
        node.setSort(unitMenus.getSort());
        node.setType(unitMenus.getType());
        node.setLinks(unitMenus.getLinks());
        node.setBeanName(unitMenus.getBeanName());
        node.setDataScope(unitMenus.getDataScope());
        node.setShowHide(unitMenus.getShowHide());
        node.setIcon(unitMenus.getIcon());
        return node;
      }).sorted(Comparator.comparingInt(UnitMenuTree::getSort))
        .collect(Collectors.toList());
      return new R<>(TreeUtil.bulid(unitMenuTreeList, "-1"));
     }

    /**
     * 通过ID查询菜单的详细信息
     *
     * @param id 菜单ID
     * @return 菜单详细信息
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable String id) {
        return new R<>(unitMenuService.getById(id));
    }

    /**
     * 新增菜单
     *
     * @param unitMenu 菜单信息
     * @return success/false
     */
    @SysLog("新增菜单")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('unit_menu_add')")
    public R save(@Valid @RequestBody UnitMenu unitMenu) {
        return unitMenuService.saveMenu(unitMenu);
    }


    /**
     * 更新菜单
     *
     * @param unitMenu
     * @return
     */
    @SysLog("更新菜单")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('unit_menu_edit')")
    public R update(@Valid @RequestBody UnitMenu unitMenu) {
        return unitMenuService.updateMenuById(unitMenu);
    }

    /**
     * 删除菜单
     *
     * @param id 菜单ID
     * @return success/false
     */
    @SysLog("删除菜单")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('unit_menu_del')")
    public R removeById(@PathVariable String id) {
        return unitMenuService.removeMenuById(id);
    }

}
