package com.cloud.portal.area.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author ryt
 * @date Created in 2019/5/27 14:27
 * @description: 区域实体
 * @modified By:
 */
@TableName("sys_area")
@Data
@EqualsAndHashCode(callSuper = true)
public class Area extends Model<Area> {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 父id
     */
    private String parentId;
    /**
     * 区域编码
     */
    private String code;
    /**
     * 区域名称
     */
    private String name;
    /**
     * 全称
     */
    private String fullName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 区域类型（1：国家；2：省份、直辖市；3：地市；4：区县）
     */
    private String type;
    /**
     * 父级ids
     */
    private String parentIds;

    private String remarks;

    /**
     * 0-正常，1-删除
     */
    @TableLogic
    private String delFlag;

    private String createBy;
    private String updateBy;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @TableField(exist = false)
    private String flag;
    /**
     * 分级（主要查询对应的级别）
     */
    @TableField(exist = false)
    private Integer level;
}
