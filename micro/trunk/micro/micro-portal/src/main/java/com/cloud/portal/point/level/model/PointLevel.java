package com.cloud.portal.point.level.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author ryt
 * @date Created in 2019/6/21 10:06
 * @description: 积分等级评定
 * @modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("T_THCS_POINT_LEVEL")
public class PointLevel extends Model<PointLevel> {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    /**
     * 方案名称
     */
    private String name;
    /**
     * 状态 字典（0-启用 1停用）
     */
    private String status;
    /**
     * 是否通用（0 是 1否 ）
     */
    private String universal;
    /**
     * 适用场所字典（ 七行八所）
     */
    private String placeType;
    /**
     * 适用地区字典（21个地市）
     */
    private String cityCode;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 创建时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
    /**
     * 删除标识 字典（0 正常 1已删除）
     */
    @TableLogic(value = "0", delval = "1")
    private String delFlag;

    /**
     * 创建人姓名
     */
    private String creator;
    /**
     * 修改人
     */
    private String updater;

    /**
     * 创建者所属部门
     */
    private String deptCode;

    @TableField(exist = false)
    List<PointLevelChild> pointLevelChildList;

    /**
     * 数据权限
     */
    @TableField(exist = false)
    @JsonIgnore
    private String dataScope;

}
