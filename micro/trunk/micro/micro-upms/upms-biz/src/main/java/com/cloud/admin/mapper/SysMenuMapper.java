

package com.cloud.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.admin.api.entity.SysMenu;
import com.cloud.admin.api.vo.MenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author ygnet
 * @since 2017-10-29
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	/**
	 * 通过角色编号查询菜单
	 *
	 * @param roleId 角色ID
	 * @return
	 */
	List<MenuVO> listMenusByRoleId(Integer roleId);

	/**
	 * 通过角色ID查询权限
	 *
	 * @param roleIds Ids
	 * @return
	 */
	List<String> listPermissionsByRoleIds(String roleIds);

	/**
	 * 更新子菜单所有的父ID信息
	 * @param oldParentIds
	 * @param newParentIds
	 * @param showHide
	 * @return
	 */
	Boolean updateChild(@Param("showHide") String showHide,@Param("oldParentIds") String oldParentIds,@Param("newParentIds")String newParentIds);

	/**
	 * 删除当前ID级其子级
	 * @param menuId
	 * @param parentIds
	 * @return
	 */
	Boolean deleteChild(@Param("menuId")String menuId,@Param("parentIds")String parentIds);

	/**
	 * 获取当前用户菜单
	 * @param roleId
	 * @param name
	 * @return
	 */
	List<MenuVO> findUserMenu(@Param("roleId") Integer roleId,@Param("name")String name);

	/**
	 * 通过角色ID 获取该角色下所有的模型
	 * @param roleId
	 * @return
	 */
	List<SysMenu> listModelMenusByRoleId(Integer roleId);

	/**
	 * 还原已删除的菜单信息
	 *
	 * @param id
	 * @return success/false
	 */
	Boolean rollbackById(@Param("id")String id);
}
