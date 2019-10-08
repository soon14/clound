

package com.cloud.admin.api.dto;

import com.cloud.admin.api.entity.SysMenu;
import com.cloud.admin.api.vo.MenuVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ygnet
 * @date 2017年11月9日23:33:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuTree extends TreeNode {
    private String icon;
    private String name;
    private boolean spread = false;
    private String path;
    private String component;
    private String authority;
    private String redirect;
    private String keepAlive;
    private String code;
    private String type;
    private String label;
    private Integer sort;
    private String permission;
    private String parentIds;

    public MenuTree() {
    }

    public MenuTree(String id, String name, String parentId) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.label = name;
    }

    public MenuTree(String id, String name, MenuTree parent) {
        this.id = id;
        this.parentId = parent.getId();
        this.name = name;
        this.label = name;
    }

    public MenuTree(MenuVO menuVo) {
        this.id = menuVo.getMenuId();
        this.parentId = menuVo.getParentId();
        this.icon = menuVo.getIcon();
        this.name = menuVo.getName();
        this.path = menuVo.getPath();
        this.component = menuVo.getComponent();
        this.type = menuVo.getType();
        this.label = menuVo.getName();
        this.sort = menuVo.getSort()==null?0:menuVo.getSort();
        this.keepAlive = menuVo.getKeepAlive();
        this.permission = menuVo.getPermission();
        this.parentIds=menuVo.getParentIds();
    }

    public MenuTree(SysMenu sysMenu) {
        this.id = sysMenu.getMenuId();
        this.parentId = sysMenu.getParentId();
        this.icon = sysMenu.getIcon();
        this.name = sysMenu.getName();
        this.path = sysMenu.getPath();
        this.component = sysMenu.getComponent();
        this.type = sysMenu.getType();
        this.label = sysMenu.getName();
        this.sort = sysMenu.getSort()==null?0:sysMenu.getSort();
        this.keepAlive = sysMenu.getKeepAlive();
        this.permission = sysMenu.getPermission();
        this.parentIds=sysMenu.getParentIds();
    }
}
