
package com.cloud.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.admin.api.entity.SysRoleMenuScope;

import java.util.List;

/**
 * 自定义角色某类菜单权限
 *
 * @author wengshij
 * @date 2019-02-25 10:35:00
 */
public interface SysRoleMenuScopeService extends IService<SysRoleMenuScope> {

    /**
     * 根据角色ID获取自定义菜单权限
     * @param roleId
     * @return
     */
   List<SysRoleMenuScope> findRoleMenuScopeByRoleId(Integer roleId);

}
