

package com.cloud.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.admin.api.dto.MenuTree;
import com.cloud.admin.api.entity.SysMenu;
import com.cloud.admin.api.vo.MenuVO;
import com.cloud.common.core.util.R;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author ygnet
 * @since 2017-10-29
 */
public interface SysMenuService extends IService<SysMenu> {
    /**
     * 通过角色编号查询URL 权限
     *
     * @param roleId 角色ID
     * @return 菜单列表
     */
    List<MenuVO> findMenuByRoleId(Integer roleId);

    /**
     * 级联删除菜单
     *
     * @param id 菜单ID
     * @return 成功、失败
     */
    R removeMenuById(String id);

    /**
     * 更新菜单信息
     *
     * @param sysMenu 菜单信息
     * @return 成功、失败
     */
    R updateMenuById(SysMenu sysMenu);

    /**
     * 保存菜单
     *
     * @param sysMenu
     * @return
     */
    R saveMenu(SysMenu sysMenu);

    /**
     * 获取用户菜单列表
     *
     * @param roleId
     * @param name
     * @return
     */
    List<MenuVO> findUserMenu(Integer roleId, String name);

    /**
     * 获取用户菜单
     *
     * @return
     */
    List<MenuTree> getUserMenu();

    /**
     * 获取菜单列表
     *
     * @param sysMenu
     * @return
     */
    List<MenuTree> getTree(SysMenu sysMenu);

    /**
     * 获取所有菜单信息
     *
     * @param cacheKey 缓存key值
     * @return
     */
    List<SysMenu> findAll(String cacheKey);

    /**
     * 业务类型 是研判模型类型
     * 不影响之前业务 开出的另外微服务接口方法
     * 模型菜单数据插入
     *
     * @param sysMenu
     * @return
     */
    R insertModelMenu(SysMenu sysMenu);

    /**
     * 业务类型 是研判模型类型
     * 不影响之前业务 开出的另外微服务接口方法
     * 模型菜单数据更新
     *
     * @param sysMenu
     * @return
     */
    R updateModelMenu(SysMenu sysMenu);

    /**
     * 业务类型 是研判模型类型
     * 不影响之前业务 开出的另外微服务接口方法
     * 模型菜单删除
     *
     * @param ids
     * @return
     */
    R removeModelMenu(String ids);

    /**
     * 业务类型 是研判模型类型
     * 不影响之前业务 开出的另外微服务接口方法
     * 还原已删除的菜单信息
     *
     * @param ids
     * @return
     */
    R modelRollback(String ids);

    /**
     * 根据角色获取 所有该角色的所有模型菜单集合
     *
     * @param roleId
     * @return
     */
    List<SysMenu> findModelMenuList(Integer roleId);


}
