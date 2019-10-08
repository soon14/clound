

package com.cloud.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.api.entity.SysRole;
import com.cloud.admin.service.SysRoleMenuScopeService;
import com.cloud.admin.service.SysRoleMenuService;
import com.cloud.admin.service.SysRoleService;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ygnet
 * @date 2017/11/5
 */
@RestController
@AllArgsConstructor
@RequestMapping("/role")
@Api(value = "role", description = "角色管理模块")
public class RoleController {
    private final SysRoleService sysRoleService;
    private final SysRoleMenuService sysRoleMenuService;
    private final SysRoleMenuScopeService sysRoleMenuScopeService;


    /**
     * 通过ID查询角色信息
     *
     * @param id ID
     * @return 角色信息
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id) {
        return new R<>(sysRoleService.getById(id));
    }

    /**
     * 添加角色
     *
     * @param sysRole 角色信息
     * @return success、false
     */
    @SysLog("添加角色")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys_role_add')")
    public R save(@Valid @RequestBody SysRole sysRole) {
        return sysRoleService.saveRole(sysRole);
    }

    /**
     * 修改角色
     *
     * @param sysRole 角色信息
     * @return success/false
     */
    @SysLog("修改角色")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys_role_edit')")
    public R update(@Valid @RequestBody SysRole sysRole) {
        return sysRoleService.updateRole(sysRole);
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @SysLog("删除角色")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_role_del')")
    public R removeById(@PathVariable Integer id) {
        return new R<>(sysRoleService.removeRoleById(id));
    }

    /**
     * 获取角色列表
     *
     * @return 角色列表
     */
    @GetMapping("/list")
    @SysLog("角色查询")
    public R listRoles(SysRole sysRole) {
        return new R<>(sysRoleService.findList(sysRole));
    }

    /**
     * 分页查询角色信息
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    @SysLog("角色查询")
    public R getRolePage(Page page, SysRole sysRole) {
        return new R<>(sysRoleService.findRolePage(page, sysRole));
    }


    @SysLog("更新角色菜单")
    @PutMapping("/roleMenu")
    @PreAuthorize("@pms.hasPermission('sys_role_perm')")
    public R saveRoleMenus(@RequestBody SysRole sysRole) {
        return new R<>(sysRoleMenuService.saveRoleMenus(sysRole));
    }


    @GetMapping("/roleMenuScope/{roleId}")
    public List getRoleTree(@PathVariable Integer roleId) {
        return sysRoleMenuScopeService.findRoleMenuScopeByRoleId(roleId);
    }

    /**
     * 根据角色标识查询信息
     * @param roleCode 角色标识
     * @return
     */
    @GetMapping("/details/{roleCode}")
    public R role(@PathVariable String roleCode) {
        SysRole condition = new SysRole();
        condition.setRoleCode(roleCode);
        return new R<>(sysRoleService.getOne(new QueryWrapper<>(condition)));
    }
}
