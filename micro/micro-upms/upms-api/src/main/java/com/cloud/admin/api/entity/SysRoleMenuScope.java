
package com.cloud.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 自定义角色某类菜单权限
 *
 * @author wengshij
 * @date 2019-02-25 10:35:00
 */
@Data
@TableName("sys_role_menu_scope")
@EqualsAndHashCode(callSuper = true)
public class SysRoleMenuScope extends Model<SysRoleMenuScope> {
private static final long serialVersionUID = 1L;

    /**
   * 菜单ID
   */
    private String menuId;
    /**
   * 角色ID
   */
    private Integer roleId;
    /**
   * 权限类型（ds_scope）
   */
    private String dsType;
    /**
   * 存储自定义权限 部门ID
   */
    private String dsScope;
    /**
   * 主键ID
   */
    @TableId(value = "id",type = IdType.UUID)
    private String id;
    /**
     * 菜单权限标识
     */
    private String permission;
  
}
