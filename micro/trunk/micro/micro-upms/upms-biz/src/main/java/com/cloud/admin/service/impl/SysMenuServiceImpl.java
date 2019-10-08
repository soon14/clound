

package com.cloud.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.dto.MenuTree;
import com.cloud.admin.api.entity.SysMenu;
import com.cloud.admin.api.entity.SysRoleMenu;
import com.cloud.admin.api.entity.SysRoleMenuScope;
import com.cloud.admin.api.vo.MenuVO;
import com.cloud.admin.mapper.SysMenuMapper;
import com.cloud.admin.mapper.SysRoleMenuMapper;
import com.cloud.admin.mapper.SysRoleMenuScopeMapper;
import com.cloud.admin.service.SysMenuService;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.core.util.R;
import com.cloud.common.security.util.SecurityUtils;
import com.google.common.base.Splitter;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.modelmbean.ModelMBean;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author ygnet
 * @since 2017-10-29
 */
@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    private final SysRoleMenuMapper sysRoleMenuMapper;

    private final SysRoleMenuScopeMapper sysRoleMenuScopeMapper;

    @Override
    @Cacheable(value = "menu_details", key = "#roleId  + '_menu'")
    public List<MenuVO> findMenuByRoleId(Integer roleId) {
        return baseMapper.listMenusByRoleId(roleId);
    }

    @Override
    @Cacheable(value = "menu_details", key = "#cacheKey")
    public List<SysMenu> findAll(String cacheKey) {
        return this.list(Wrappers.emptyWrapper());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "menu_details", allEntries = true)
    public R removeMenuById(String id) {
        // 查询父节点为当前节点的节点
        SysMenu tempSysMenu = this.getById(id);
        if (null == tempSysMenu) {
            return R.builder()
                    .code(CommonConstants.FAIL)
                    .msg("无法找到该节点").build();
        }

        String[] ids = (tempSysMenu.getParentIds() + id).split(",");

        sysRoleMenuMapper
                .delete(Wrappers.<SysRoleMenu>query()
                        .lambda().in(SysRoleMenu::getMenuId, ids));
        sysRoleMenuScopeMapper.delete(Wrappers.<SysRoleMenuScope>query()
                .lambda().in(SysRoleMenuScope::getMenuId, ids));
        //删除当前菜单及其子菜单
        return new R(baseMapper.deleteChild(id, tempSysMenu.getParentIds() + id + ","));
    }

    @Override
    @CacheEvict(value = "menu_details", allEntries = true)
    public R updateMenuById(SysMenu sysMenu) {


        SysMenu tempSysMenu = this.getById(sysMenu.getMenuId());
        sysMenu = initParentIds(sysMenu);
        if (StringUtils.isBlank(sysMenu.getParentId()) || StringUtils.isBlank(sysMenu.getParentIds()) || null == tempSysMenu) {
            return R.builder()
                    .code(CommonConstants.FAIL)
                    .msg("父级ID为空").build();

        }
        String[] parentIds = StringUtils.split(sysMenu.getParentIds(), ",");
        if (Arrays.asList(parentIds).contains(sysMenu.getMenuId())) {
            return R.builder()
                    .code(CommonConstants.FAIL)
                    .msg("父级菜单不能为当前菜单或其子级").build();
        }
        baseMapper.updateChild(sysMenu.getShowHide(), tempSysMenu.getParentIds() + tempSysMenu.getMenuId() + ",", sysMenu.getParentIds() + sysMenu.getMenuId() + ",");
        return new R(this.updateById(sysMenu));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "menu_details", allEntries = true)
    public R saveMenu(SysMenu sysMenu) {
        sysMenu = initParentIds(sysMenu);
        if (StringUtils.isBlank(sysMenu.getParentId()) || StringUtils.isBlank(sysMenu.getParentIds())) {
            return R.builder()
                    .code(CommonConstants.FAIL)
                    .msg("父级ID为空").build();
        }

        return new R(this.save(sysMenu));
    }

    @Override
    public List<MenuVO> findUserMenu(Integer roleId, String name) {
        return baseMapper.findUserMenu(roleId, name);
    }


    @Override
    public List<MenuTree> getUserMenu() {
        List<MenuTree> menuTreeList;
        if (SecurityUtils.getUser().isAdmin()) {
            List<SysMenu> sysMenuList = this.list(Wrappers.emptyWrapper());
            menuTreeList = sysMenuList.stream()
                    .filter(sysMenu -> CommonConstants.MENU.equals(sysMenu.getType()))
                    .map(MenuTree::new)
                    .sorted(Comparator.comparingInt(MenuTree::getSort))
                    .collect(Collectors.toList());
            return menuTreeList;
        } else {
            // 获取符合条件的菜单
            List<MenuVO> all = new ArrayList<>();
            SecurityUtils.getRoles()
                    .forEach(roleId -> all.addAll(this.findMenuByRoleId(roleId)));
            menuTreeList = all.stream()
                    .filter(menuVo -> CommonConstants.MENU.equals(menuVo.getType()))
                    .map(MenuTree::new)
                    .sorted(Comparator.comparingInt(MenuTree::getSort))
                    .collect(Collectors.toList());
        }
        return menuTreeList;

    }

    @Override
    public List<MenuTree> getTree(SysMenu sysMenu) {
        List<MenuTree> menuTreeList;
        if (SecurityUtils.getUser().isAdmin()) {
            List<SysMenu> sysMenuList = this.list(Wrappers.query(new SysMenu()).like(
                    StringUtils.isNotBlank(sysMenu.getName()), SysMenu::getName, sysMenu.getName()));

            menuTreeList = sysMenuList.stream()
                    .map(menuVo -> new MenuTree(menuVo))
                    .sorted(Comparator.comparingInt(MenuTree::getSort))
                    .collect(Collectors.toList());
        } else {
            Set<MenuVO> all = new HashSet<>();
            if (StringUtils.isBlank(sysMenu.getName())) {
                SecurityUtils.getRoles()
                        .forEach(roleId -> all.addAll(this.findMenuByRoleId(roleId)));
            } else {
                SecurityUtils.getRoles()
                        .forEach(roleId -> all.addAll(this.findUserMenu(roleId, sysMenu.getName())));
            }
            menuTreeList = all.stream()
                    .map(menuVo -> new MenuTree(menuVo))
                    .sorted(Comparator.comparingInt(MenuTree::getSort))
                    .collect(Collectors.toList());
        }
        return menuTreeList;

    }


    private SysMenu initParentIds(SysMenu sysMenu) {
        if (StringUtils.isBlank(sysMenu.getParentId()) || "-1".equals(sysMenu.getParentId())) {
            sysMenu.setParentId("-1");
            sysMenu.setParentIds("-1,");
        } else {
            SysMenu tempSysMenu = this.getById(sysMenu.getParentId());
            if (null != tempSysMenu) {
                sysMenu.setParentIds(tempSysMenu.getParentIds() + tempSysMenu.getMenuId() + ",");
                //只要父级是隐藏状态 、其子级一定是隐藏
                if (CommonConstants.MENU_HIDE.equals(tempSysMenu.getShowHide())) {
                    sysMenu.setShowHide(CommonConstants.MENU_HIDE);
                }
            }
        }
        if (StringUtils.isBlank(sysMenu.getShowHide())) {
            sysMenu.setShowHide(CommonConstants.MENU_SHOW);
        }
        if (StringUtils.isBlank(sysMenu.getBussType())) {
            sysMenu.setBussType(CommonConstants.MENU_BUSS_TYPE_SYS);
        }

        return sysMenu;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "menu_details", allEntries = true)
    public R insertModelMenu(SysMenu sysMenu) {
        if (null == sysMenu || StringUtils.isBlank(sysMenu.getMenuId())) {
            return R.builder().code(CommonConstants.FAIL).msg("数据缺失").data(Boolean.FALSE).build();
        }
        return new R(this.save(initModelMenu(sysMenu)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "menu_details", allEntries = true)
    public R updateModelMenu(SysMenu sysMenu) {
        if (null == sysMenu || StringUtils.isBlank(sysMenu.getMenuId())) {
            return R.builder().code(CommonConstants.FAIL).msg("数据缺失").data(Boolean.FALSE).build();
        }
        return new R(this.updateById(sysMenu));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "menu_details", allEntries = true)
    public R removeModelMenu(String ids) {
        String[] arrId = StringUtils.isNotBlank(ids) ? ids.split(",") : null;
        if (StringUtils.isBlank(ids) || null == arrId || arrId.length <= 0) {
            return R.builder().code(CommonConstants.FAIL).msg("数据缺失").data(Boolean.FALSE).build();
        } else {
            sysRoleMenuMapper
                    .delete(Wrappers.<SysRoleMenu>query()
                            .lambda().in(SysRoleMenu::getMenuId, arrId));
            sysRoleMenuScopeMapper.delete(Wrappers.<SysRoleMenuScope>query()
                    .lambda().in(SysRoleMenuScope::getMenuId, arrId));
            if (arrId.length == 1) {
                return new R(this.removeById(arrId[0]));
            } else {
                return new R(this.removeByIds(Arrays.asList(arrId)));
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "menu_details", allEntries = true)
    public R modelRollback(String ids){
        String[] arrId = StringUtils.isNotBlank(ids) ? ids.split(",") : null;
        if (StringUtils.isBlank(ids) || null == arrId || arrId.length <= 0) {
            return R.builder().code(CommonConstants.FAIL).msg("数据缺失").data(Boolean.FALSE).build();
        }else {
            if (arrId.length == 1) {
                return new R(this.baseMapper.rollbackById(arrId[0]));
            } else {
                for(int i=0;i<Arrays.asList(arrId).size();i++){
                    this.baseMapper.rollbackById(Arrays.asList(arrId).get(i));
                }
                return  new R();
            }
        }
    }

    @Override
    @Cacheable(value = "menu_details", key = "'model_'+#roleId")
    public List<SysMenu> findModelMenuList(Integer roleId) {
        return this.baseMapper.listModelMenusByRoleId(roleId);
    }

    private SysMenu initModelMenu(SysMenu sysMenu) {
        sysMenu.setIcon("el-icon-goods");
        sysMenu.setKeepAlive(CommonConstants.KEEP_ALIVE_OPEN);
        sysMenu.setParentId(CommonConstants.SYS_MENU_MODEL_ID);
        sysMenu.setType(CommonConstants.MENU);
        sysMenu.setParentIds(CommonConstants.SYS_MENU_MODEL_PARENT_IDS);
        sysMenu.setBussType(CommonConstants.MENU_BUSS_TYPE_MODEL);
        sysMenu.setShowHide(CommonConstants.MENU_SHOW);
        sysMenu.setDelFlag(CommonConstants.STATUS_NORMAL);
        sysMenu.setCreateTime(LocalDateTime.now());
        sysMenu.setUpdateTime(LocalDateTime.now());
        return sysMenu;
    }

}
