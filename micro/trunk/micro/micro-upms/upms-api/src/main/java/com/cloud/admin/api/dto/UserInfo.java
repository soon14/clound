

package com.cloud.admin.api.dto;

import com.cloud.admin.api.entity.SysDept;
import com.cloud.admin.api.entity.SysRole;
import com.cloud.admin.api.entity.SysUser;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ygnet
 * @date 2017/11/11
 * <p>
 * commit('SET_ROLES', data)
 * commit('SET_NAME', data)
 * commit('SET_AVATAR', data)
 * commit('SET_INTRODUCTION', data)
 * commit('SET_PERMISSIONS', data)
 */
@Data
public class UserInfo implements Serializable {
	/**
	 * 用户基本信息
	 */
	private SysUser sysUser;
	/**
	 * 权限标识集合
	 */
	private String[] permissions;

	/**
	 * 角色集合
	 */
	private Integer roles;
	/**
	 * 当前用户部门信息
	 */
	private SysDept sysDept;
	/**
	 * 当前用户角色信息
	 */
	private SysRole sysRole;
}
