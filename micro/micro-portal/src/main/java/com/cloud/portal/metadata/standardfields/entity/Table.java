package com.cloud.portal.metadata.standardfields.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.cloud.admin.api.annotation.LogField;
import com.cloud.portal.metadata.standardfields.constant.TableKindEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author ryt
 * @date Created in 2019/4/9 15:25
 * @description: 标准字段管理 数据表entity
 * @modified By:
 */
@Data
@TableName("t_datasource_table")
@EqualsAndHashCode(callSuper = true)
public class Table extends Model<Table> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 数据库id
     */
    private String dbId;
    /**
     * 表英文名
     */
    @LogField(title = "表英文名")
    private String name;
    /**
     * 表中文名
     */
    private String cName;
    /**
     * 类型
     */
    @LogField(title = "类型")
    private TableKindEnum type;
    /**
     * 创建时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 更新时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 0-正常，1-删除
     */
    @TableLogic
    private String delFlag;
    /**
     * 备注
     */
    private String remarks;

    /**
     * 数据库名称
     */
    @TableField(exist = false)
    private String dbName;
    /**
     * 数据库类型
     */
    @TableField(exist = false)
    private String dbType;

    /**
     * 源表
     */
    @TableField(exist = false)
    List<Map<String,String>> tableList;
}
