package com.cloud.portal.point.index.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author wengshij
 * @date Created in 2019/6/20 10:06
 * @description: 积分指标（手动积分与自动积分）
 * @modified By:wengshij
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("T_THCS_POINT_INDEX")
public class PointIndex extends Model<PointIndex> {

    /**
     * 适用地区字典（21个地市）
     */
    private String city;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 删除标识 字典（0 正常 1已删除）
     */
    @TableLogic(value = "0", delval = "1")
    private String delFlag;
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 指标名称
     */
    private String name;
    /**
     * 适用场所字典（ 七行八所）
     */
    private String placeType;
    /**
     * 指标规则说明
     */
    private String remark;
    /**
     * 指标分值
     */
    private Integer score;
    /**
     * 指标状态字典（0 启用 1停用 ）
     */
    private String status;
    /**
     * 指标类型字典（0-自动 1-手动 ）
     */
    private String type;
    /**
     * 积分指标标识符(手动积分自动填充 - 自动积分选字典)
     */
    @TableField(value = "udId")
    private String udId;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 上限次数（周期内 ）0 表示无限次
     */
    private Integer upperLimit;
    /**
     * 版本号
     */
    private String version;
    /**
     * 创建者所属部门
     */
    private String deptCode;
    /**
     * 创建者姓名
     */
    private String creator;
    /**
     * 更新者姓名
     */
    private String updater;
    /**
     * 数据权限简单控制
     */
    @TableField(exist = false)
    @JsonIgnore
    private String dataScope;

}
