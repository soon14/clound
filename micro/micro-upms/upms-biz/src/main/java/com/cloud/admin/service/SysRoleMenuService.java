

package com.cloud.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.admin.api.entity.SysRole;
import com.cloud.admin.api.entity.SysRoleMenu;
import com.cloud.common.core.util.R;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author ygnet
 * @since 2017-10-29
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {



	/**
	 * 保存角色信息菜单信息
	 * @param sysRole
	 * @return
	 */
	R saveRoleMenus(SysRole sysRole);
}
