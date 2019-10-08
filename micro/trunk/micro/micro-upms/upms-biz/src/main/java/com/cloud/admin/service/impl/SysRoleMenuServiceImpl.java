

package com.cloud.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.entity.SysRole;
import com.cloud.admin.api.entity.SysRoleMenu;
import com.cloud.admin.api.entity.SysRoleMenuScope;
import com.cloud.admin.mapper.SysRoleMenuMapper;
import com.cloud.admin.service.SysRoleMenuScopeService;
import com.cloud.admin.service.SysRoleMenuService;
import com.cloud.admin.util.OptUtil;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.core.util.R;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author ygnet
 * @since 2017-10-29
 */
@Service
@AllArgsConstructor
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    private final CacheManager cacheManager;


    @Autowired
    private SysRoleMenuScopeService sysRoleMenuScopeService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "menu_details", key = "#sysRole.roleId + '_menu'")
    public R saveRoleMenus(SysRole sysRole) {
        R r = OptUtil.check(sysRole);
        if (null != r) {
            return r;
        }
        this.remove(Wrappers.<SysRoleMenu>query().lambda()
                .eq(SysRoleMenu::getRoleId, sysRole.getRoleId()));

        if (StrUtil.isBlank(sysRole.getMenuIds())) {
            return R.builder()
                    .code(CommonConstants.SUCCESS).build();
        }
        List<SysRoleMenu> roleMenuList = Arrays
                .stream(sysRole.getMenuIds().split(","))
                .map(menuId -> {
                    SysRoleMenu roleMenu = new SysRoleMenu();
                    roleMenu.setRoleId(sysRole.getRoleId());
                    roleMenu.setMenuId(menuId);
                    return roleMenu;
                }).collect(Collectors.toList());
        //清除所有菜单权限
        sysRoleMenuScopeService.remove(Wrappers.<SysRoleMenuScope>query().lambda().eq(SysRoleMenuScope::getRoleId, sysRole.getRoleId()));
        List<SysRoleMenuScope> sysRoleMenuScopeList = sysRole.getRoleMenuScopeList();
        if (null != sysRoleMenuScopeList && sysRoleMenuScopeList.size() > 0) {
            for (SysRoleMenuScope sysRoleMenuScope : sysRoleMenuScopeList) {
                sysRoleMenuScope.setRoleId(sysRole.getRoleId());
            }
            sysRoleMenuScopeService.saveBatch(sysRoleMenuScopeList);
        }

        //清空userinfo
        cacheManager.getCache("user_details").clear();
        cacheManager.getCache("menu_details").clear();
        cacheManager.getCache("modelMenu_details").clear();
        return new R(this.saveBatch(roleMenuList));
    }



}
