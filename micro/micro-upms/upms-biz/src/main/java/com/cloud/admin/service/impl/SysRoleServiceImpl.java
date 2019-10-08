

package com.cloud.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.entity.SysRole;
import com.cloud.admin.api.entity.SysRoleMenu;
import com.cloud.admin.api.entity.SysRoleMenuScope;
import com.cloud.admin.mapper.SysRoleMapper;
import com.cloud.admin.mapper.SysRoleMenuMapper;
import com.cloud.admin.service.SysRoleMenuScopeService;
import com.cloud.admin.service.SysRoleService;
import com.cloud.admin.util.OptUtil;
import com.cloud.common.core.util.R;
import com.cloud.common.data.datascope.DataScope;
import com.cloud.common.data.util.DataScopeUtil;
import com.cloud.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ygnet
 * @since 2017-10-29
 */
@Service
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    private SysRoleMenuMapper sysRoleMenuMapper;

    private SysRoleMenuScopeService sysRoleMenuScopeService;

    /**
     * 通过用户ID，查询角色信息
     *
     * @param userId
     * @return
     */
    @Override
    public List findRolesByUserId(Integer userId) {
        return baseMapper.listRolesByUserId(userId);
    }

    /**
     * 通过角色ID，删除角色,并清空角色菜单缓存
     *
     * @param id
     * @return
     */
    @Override
    @CacheEvict(value = "menu_details", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeRoleById(Integer id) {
        sysRoleMenuMapper.delete(Wrappers
                .<SysRoleMenu>update().lambda()
                .eq(SysRoleMenu::getRoleId, id));
        //删除当前角色下配置的菜单权限
        sysRoleMenuScopeService.remove(Wrappers.<SysRoleMenuScope>query().lambda().eq(SysRoleMenuScope::getRoleId, id));

        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R saveRole(SysRole sysRole) {
        R r = OptUtil.check(sysRole);
        if (null != r) {
            return r;
        }
        sysRole.setUpdateBy(SecurityUtils.getUser().getId());
        sysRole.setCreateBy(sysRole.getUpdateBy());
        return new R(this.save(sysRole));
    }

    @Override
    public R updateRole(SysRole sysRole) {
        R r = OptUtil.check(sysRole);
        if (null != r) {
            return r;
        }
        sysRole.setUpdateBy(SecurityUtils.getUser().getId());
        return new R(this.updateById(sysRole));
    }

    @Override
    public IPage findRolePage(Page page, SysRole sysRole) {
        sysRole.setDataScope(DataScopeUtil.getDataScopeSql(new DataScope("sd.code", "sys_role_list", "su.user_id")));
        return baseMapper.findRolePage(page, sysRole);
    }

    @Override
    public List<SysRole> findList(SysRole sysRole) {
        if (null == sysRole) {
            sysRole = new SysRole();
        }
        sysRole.setDataScope(DataScopeUtil.getDataScopeSql(new DataScope("sd.code", "sys_role_list", "su.user_id")));
        return baseMapper.findList(sysRole);
    }
}
