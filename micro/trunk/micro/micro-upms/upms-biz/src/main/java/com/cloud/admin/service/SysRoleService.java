

package com.cloud.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.admin.api.dto.UserDTO;
import com.cloud.admin.api.entity.SysRole;
import com.cloud.admin.api.vo.UserVO;
import com.cloud.common.core.util.R;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ygnet
 * @since 2017-10-29
 */
public interface SysRoleService extends IService<SysRole> {

	/**
	 * 通过用户ID，查询角色信息
	 *
	 * @param userId
	 * @return
	 */
	List<SysRole> findRolesByUserId(Integer userId);

	/**
	 * 通过角色ID，删除角色
	 *
	 * @param id
	 * @return
	 */
	Boolean removeRoleById(Integer id);

	/**
	 * 创建角色
	 * @param sysRole
	 * @return
	 */
	R saveRole(SysRole sysRole);

	/**
	 * 更新角色信息
	 * @param sysRole
	 * @return
	 */
	R  updateRole(SysRole sysRole);

	/**
	 * 获取角色列表
	 * @param page
	 * @param sysRole
	 * @return
	 */
	IPage findRolePage(Page page, SysRole sysRole);

	/**
	 * 获取角色列表
	 * @param sysRole
	 * @return
	 */
	List<SysRole> findList(SysRole sysRole);

}
