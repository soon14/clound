

package com.cloud.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.admin.api.dto.MenuTree;
import com.cloud.admin.api.entity.SysMenu;
import com.cloud.admin.api.vo.MenuVO;
import com.cloud.admin.api.vo.TreeUtil;
import com.cloud.admin.service.SysMenuService;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.common.security.util.SecurityUtils;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ygnet
 * @date 2017/10/31
 */
@RestController
@AllArgsConstructor
@RequestMapping("/menu")
@Api(value = "menu", description = "菜单管理模块")
public class MenuController {
    private final SysMenuService sysMenuService;

    /**
     * 返回当前用户的树形菜单集合
     *
     * @return 当前用户的树形菜单
     */
    @GetMapping
    public R getUserMenu() {
        List<MenuTree> menuTreeList;
        if (SecurityUtils.getUser().isAdmin()) {
            List<SysMenu> sysMenuList = sysMenuService.findAll(CommonConstants.ALL_MENU_CACHE_KEY);
            menuTreeList = sysMenuList.stream()
                    .filter(sysMenu -> CommonConstants.MENU.equals(sysMenu.getType()) && CommonConstants.MENU_SHOW.equals(sysMenu.getShowHide())
                            && CommonConstants.MENU_BUSS_TYPE_SYS.equals(sysMenu.getBussType())

                    )
                    .map(MenuTree::new)
                    .sorted(Comparator.comparingInt(MenuTree::getSort))
                    .collect(Collectors.toList());
        } else {
            // 获取符合条件的菜单
            List<MenuVO> all = new ArrayList<>();
            SecurityUtils.getRoles()
                    .forEach(roleId -> all.addAll(sysMenuService.findMenuByRoleId(roleId)));
            menuTreeList = all.stream()
                    .filter(menuVo -> CommonConstants.MENU.equals(menuVo.getType())
                            && CommonConstants.MENU_SHOW.equals(menuVo.getShowHide())
                            && CommonConstants.MENU_BUSS_TYPE_SYS.equals(menuVo.getBussType())
                    )
                    .map(MenuTree::new)
                    .sorted(Comparator.comparingInt(MenuTree::getSort))
                    .collect(Collectors.toList());
        }
        return new R<>(TreeUtil.bulid(menuTreeList, "-1"));

    }

    /**
     * 返回树形菜单集合
     *
     * @return 树形菜单
     */
    @GetMapping(value = "/tree")
    @SysLog("菜单查询")
    public R getTree(SysMenu sysMenu) {
        List<MenuTree> menuTreeList;
        if (SecurityUtils.getUser().isAdmin()) {
            List<SysMenu> sysMenuList;
            if (StringUtils.isBlank(sysMenu.getName())) {
                sysMenuList = sysMenuService.findAll(CommonConstants.ALL_MENU_CACHE_KEY);
            } else {
                sysMenuList = sysMenuService.list(Wrappers.query(new SysMenu()).like(
                        StringUtils.isNotBlank(sysMenu.getName()), SysMenu::getName, sysMenu.getName()));
            }
            menuTreeList = sysMenuList.stream().filter(sysMenu1 ->
                    CommonConstants.MENU_BUSS_TYPE_SYS.equals(sysMenu1.getBussType())
                            || (StringUtils.isNotBlank(sysMenu.getBussType()) && sysMenu.getBussType().equals(sysMenu1.getBussType()))

            )

                    .map(menuVo -> new MenuTree(menuVo))
                    .sorted(Comparator.comparingInt(MenuTree::getSort))
                    .collect(Collectors.toList());
        } else {
            Set<MenuVO> all = new HashSet<>();
            if (StringUtils.isBlank(sysMenu.getName())) {
                SecurityUtils.getRoles()
                        .forEach(roleId -> all.addAll(sysMenuService.findMenuByRoleId(roleId)));
            } else {
                SecurityUtils.getRoles()
                        .forEach(roleId -> all.addAll(sysMenuService.findUserMenu(roleId, sysMenu.getName())));
            }
            menuTreeList = all.stream().filter(sysMenu1 ->
                    CommonConstants.MENU_BUSS_TYPE_SYS.equals(sysMenu1.getBussType())
                            || (StringUtils.isNotBlank(sysMenu.getBussType()) && sysMenu.getBussType().equals(sysMenu1.getBussType()))
            )
                    .map(menuVo -> new MenuTree(menuVo))
                    .sorted(Comparator.comparingInt(MenuTree::getSort))
                    .collect(Collectors.toList());
        }
        return new R<>(TreeUtil.bulid(menuTreeList, "-1"));
    }

    /**
     * 返回角色的菜单集合
     *
     * @param roleId 角色ID
     * @return 属性集合
     */
    @GetMapping("/tree/{roleId}")
    public List getRoleTree(@PathVariable Integer roleId) {
        return sysMenuService.findMenuByRoleId(roleId)
                .stream()
                .map(MenuVO::getMenuId)
                .collect(Collectors.toList());
    }

    /**
     * 通过ID查询菜单的详细信息
     *
     * @param id 菜单ID
     * @return 菜单详细信息
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable String id) {
        return new R<>(sysMenuService.getById(id));
    }

    /**
     * 新增菜单
     *
     * @param sysMenu 菜单信息
     * @return success/false
     */
    @SysLog("新增菜单")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys_menu_add')")
    public R save(@Valid @RequestBody SysMenu sysMenu) {

        return sysMenuService.saveMenu(sysMenu);
    }

    /**
     * 删除菜单
     *
     * @param id 菜单ID
     * @return success/false
     */
    @SysLog("删除菜单")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_menu_del')")
    public R removeById(@PathVariable String id) {
        return sysMenuService.removeMenuById(id);
    }

    /**
     * 更新菜单
     *
     * @param sysMenu
     * @return
     */
    @SysLog("更新菜单")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys_menu_edit')")
    public R update(@Valid @RequestBody SysMenu sysMenu) {
        return sysMenuService.updateMenuById(sysMenu);
    }



    /*

    ########################菜单操作 -业务类型为研判模型的菜单DDL微服务操作接口###########################*/

    @SysLog("新增菜单模型")
    @PostMapping("/model/save")
    public R<Boolean> modelSave(@Valid @RequestBody SysMenu sysMenu) {
        return sysMenuService.insertModelMenu(sysMenu);
    }

    @SysLog("更新菜单模型")
    @PutMapping("/model/update")
    public R<Boolean> modelUpdate(@Valid @RequestBody SysMenu sysMenu) {
        return sysMenuService.updateModelMenu(sysMenu);
    }

    /**
     * 删除模型菜单
     *
     * @param ids 模型菜单集合逗号隔开
     * @return success/false
     */
    @SysLog("删除菜单模型")
    @DeleteMapping("/model/remove")
    public R<Boolean> modelRemove(String ids) {
        return sysMenuService.removeModelMenu(ids);
    }

    /**
     * 还原已删除的菜单信息
     *
     * @param ids 模型菜单集合逗号隔开
     * @return success/false
     */
    @SysLog("还原已删除的菜单信息")
    @PutMapping("/model/rollback")
    public R<Boolean> modelRollback(String ids) {
        return sysMenuService.modelRollback(ids);
    }

    /**
     * 返回角色的菜单模型集合
     *
     * @param roleId 角色ID
     * @return 属性集合
     */
    @GetMapping("/model/list/{roleId}")
    public R findModelMenuList(@PathVariable Integer roleId) {
        return new R(sysMenuService.findModelMenuList(roleId));
    }


}
