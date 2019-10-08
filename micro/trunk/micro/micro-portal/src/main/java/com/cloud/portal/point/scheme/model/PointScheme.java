package com.cloud.portal.point.scheme.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019/7/15
 * @description:积分方案管理
 * @modified By:lvlinc
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("T_THCS_POINT_SCHEME")
public class PointScheme extends Cycle {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 适用地区 字典（21地市）
     */
    private String cityCode;
    /**
     * 更新者
     */
    private String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 所属周期（年度 、半年度 、季度、月度）
     */
    private String cycleType;
    /**
     * 默认分值（默认100分）
     */
    private Integer defaultScore;
    /**
     * 删除标识
     */
    private String delFlag;
    /**
     * 考核开始时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String startTime;
    /**
     * 考核结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String endTime;
    /**
     * 是否初始化默认值(是否初始化分值)
     */
    private String isInit;
    /**
     * 方案名称
     */
    private String name;
    /**
     * 场所类型（七行八所）
     */
    private String placeType;
    /**
     * 状态（启用 停用）
     */
    private String status;
    /**
     * 通用（0 是 1否）
     */
    private String universal;
    /**
     * 创建者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 更新者
     */
    private String updater;
    /**
     * 创建者所属部门
     */
    private String deptCode;

    @TableField(exist = false)
    private List<Cycle> cycleList;

    private String dataScope;
    /**
     * 存储所有旅馆业的表名称
     */
    @TableField(exist = false)
    @JsonIgnore
    private List<String> tableName;
    /**
     * 等级id
     */
    private String levelId;
    /**
     * 等级类型
     */
    private String levelType;
}