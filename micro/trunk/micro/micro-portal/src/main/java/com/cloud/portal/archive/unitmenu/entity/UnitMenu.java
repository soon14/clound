package com.cloud.portal.archive.unitmenu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author ryt
 * @date Created in 2019/5/7 10:37
 * @description: 场所分类显示信息entity
 * @modified By:
 */
@Data
@TableName("t_thcs_flxx")
@EqualsAndHashCode(callSuper = true)
public class UnitMenu extends Model<UnitMenu> {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    private String name;

    private String parentId;

    private String parentIds;
    /**
     * 场所类型
     */
    private String type;
    /**
     * 排序值
     */
    private Integer sort;
    /**
     * 链接地址
     */
    private String links;
    /**
     * BEAN名称（通过反射获取 SERVICE 层）
     */
    private String beanName;
    /**
     * 权限字段
     */
    private String dataScope;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private String createBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private String updateBy;
    /**
     * 显示/隐藏
     */
    private String showHide;

    private String icon;
}
