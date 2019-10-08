package com.cloud.portal.metadata.standardfields.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.cloud.admin.api.annotation.LogField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ryt
 * @date Created in 2019/4/9 15:25
 * @description: 标准字段管理 表字段entity
 * @modified By:
 */
@Data
@TableName("t_datasource_field")
@EqualsAndHashCode(callSuper = true)
public class Field extends Model<Field> implements Cloneable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 数据表id
     */
    private String tableId;
    /**
     * 字段英文名
     */
    @LogField(title = "字段英文名")
    private String name;
    /**
     * 字段中文名
     */
    private String cName;
    /**
     * 长度
     */
    private Integer length;
    /**
     * 类型
     */
    @LogField(title = "字段类型")
    private String type;
    /**
     * 是否主键
     */
    private String pk;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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
     * 是否为空
     */
    private String notNull;

    @TableField(exist = false)
    private Table table;
    /**
     * 源表字段
     */
    @TableField(exist = false)
    List<Field> fieldList;

    @Override
    public Field clone() {
        Field field = null;
        try {
            field = (Field) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } finally {
            return field;
        }

    }
}
