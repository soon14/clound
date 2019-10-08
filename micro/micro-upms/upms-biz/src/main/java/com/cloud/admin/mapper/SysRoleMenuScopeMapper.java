
package com.cloud.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.admin.api.entity.SysRoleMenuScope;

import java.util.List;

/**
 * 自定义角色某类菜单权限
 *
 * @author wengshij
 * @date 2019-02-25 10:35:00
 */
public interface SysRoleMenuScopeMapper extends BaseMapper<SysRoleMenuScope> {

    /**
     * 根据角色ID获取自定义菜单权限
     * @param roleId
     * @return
     */
    List<SysRoleMenuScope> findRoleMenuScopeByRoleId(Integer roleId);
}
