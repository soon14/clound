

package com.cloud.portal.archive.unitmenu.entity;

import com.cloud.admin.api.dto.TreeNode;
import com.cloud.admin.api.entity.SysMenu;
import com.cloud.admin.api.vo.MenuVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ryt
 * @date Created in 2019/5/7 10:37
 * @description:
 * @modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UnitMenuTree extends TreeNode {
    private String name;
    private String parentIds;
    private String type;
    private Integer sort;
    private String links;
    private String beanName;
    private String dataScope;
    private String showHide;
    private String icon;
}
