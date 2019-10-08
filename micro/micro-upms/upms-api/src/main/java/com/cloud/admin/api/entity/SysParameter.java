package com.cloud.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 全局参数
 *
 * @author Molly
 * @since 2019-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysParameter extends Model<SysParameter> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 参数键值
     */
    @TableField(condition = SqlCondition.LIKE)
    @NotBlank(message = "参数键值不能为空")
    private String parameterKey;
    /**
     * 参数数值
     */
    @NotBlank(message = "参数数值不能为空")
    private String value;
    /**
     * 版本id
     */
    private String versionId;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private LocalDateTime createDate;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 修改时间
     */
    private LocalDateTime updateDate;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 删除标识
     */
    @TableLogic
    private String delFlag;
}
