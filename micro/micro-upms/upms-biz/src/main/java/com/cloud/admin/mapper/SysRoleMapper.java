

package com.cloud.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.api.dto.UserDTO;
import com.cloud.admin.api.entity.SysRole;
import com.cloud.admin.api.vo.UserVO;
import com.cloud.common.data.datascope.DataScope;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ygnet
 * @since 2017-10-29
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
	/**
	 * 通过用户ID，查询角色信息
	 *
	 * @param userId
	 * @return
	 */
	List<SysRole> listRolesByUserId(Integer userId);

	/**
	 * 角色 列表 包含权限信息
	 * @param page
	 * @param sysRole
	 * @return
	 */
	IPage<List<UserVO>> findRolePage(Page page, @Param("query") SysRole sysRole);

	/**
	 * 获取角色列表
	 * @param sysRole
	 * @return
	 */
	List<SysRole> findList(@Param("query") SysRole sysRole);

}
