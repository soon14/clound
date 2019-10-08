

package com.cloud.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.cloud.admin.api.annotation.LogField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author ygnet
 * @since 2017-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_id", type = IdType.AUTO)
    @LogField(title = "主键")
    private Integer roleId;

    @NotBlank(message = "角色名称 不能为空")
    @LogField(title = "角色名称")
    private String roleName;

    @NotBlank(message = "角色标识 不能为空")
    @LogField(title = "角色标识")
    private String roleCode;

    @NotBlank(message = "角色描述 不能为空")
    private String roleDesc;

    @NotNull(message = "数据权限类型 不能为空")
    @LogField(title = "角色类型",dictType = "data_scope")
    private String dsType;

    private String dsScope;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    /**
     * 删除标识（0-正常,1-删除）
     */
    @TableLogic
    private String delFlag;

    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

    @TableField(exist = false)
    List<SysRoleMenuScope> roleMenuScopeList;
    /**
     * 菜单ID集合
     */
    @TableField(exist = false)
    private String menuIds;
    /**
     * 是否锁住 0否 1是
     * 字典 yes_no
     */
    @LogField(title = "锁住",dictType = "yes_no")
    private String lockUp;

    /**
     * 创建者ID
     */
    private String createBy;
    /**
     * 更新者ID
     */
    private String updateBy;
    /**
     * 权限标识SQL
     */
    @TableField(exist = false)
    private String dataScope;
    /**
     * 角色部门等级
     */
    @TableField(exist = false)
    private String codeLevel;


}
