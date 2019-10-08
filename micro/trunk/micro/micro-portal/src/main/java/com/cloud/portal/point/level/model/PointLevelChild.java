package com.cloud.portal.point.level.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ryt
 * @date Created in 2019/6/21 10:10
 * @description: 积分等级评定子项
 * @modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("T_THCS_POINT_LEVEL_CHILD")
public class PointLevelChild extends Model<PointLevelChild> {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 方案id
     */
    private String levelId;
    /**
     * 等级名称 字典（A级 B级 C级 D级）
     */
    private String type;
    /**
     * 链接类型（区间（左闭右闭）、大于、小于）
     */
    private String linkType;
    /**
     * 左分值
     */
    private String leftScore;
    /**
     * 右分值
     */
    private String rightScore;
    /**
     * 说明
     */
    private String remark;

}
