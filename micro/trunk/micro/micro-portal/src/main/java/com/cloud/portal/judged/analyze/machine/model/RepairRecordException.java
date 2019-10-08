package com.cloud.portal.judged.analyze.machine.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author wengshij
 * @date Created in 2019/7/2 14:41
 * @description: 机修 维修记录异常分析（按天统计-每天记录少于3次视为异常）
 * @modified By:wengshij
 */
@TableName("T_THCS_ANALY_JX_XCJLYC")
@Data
@EqualsAndHashCode(callSuper = true)
public class RepairRecordException extends Model<RepairRecordException> {

    /**
     * 地址
     */
    private String address;
    /**
     * 批次ID
     */
    private String batchId;
    /**
     * 行政区划
     */
    private String cityCode;
    /**
     * 按天统计
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date dayDate;
    /**
     * 管辖派出所 代码
     */
    private String deptCode;
    /**
     * 管辖派出所名称
     */
    private String deptName;
    /**
     * 主键ID
     */
    private String id;
    /**
     * 检测时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date monitorTime;
    /**
     * 模型唯一标识
     */
    @TableField(value = "MODEL_UDID")
    private String modelUdId;
    /**
     * 修车记录
     */
    private Integer num;
    /**
     * 场所名称
     */
    private String placeName;
    /**
     * 场所主键ID
     */
    private String placeId;
    /**
     * 营业状态
     */
    private String type;
    /**
     * 权限标识信息
     */
    @JsonIgnore
    @TableField(exist = false)
    private String dataScope;
    /**
     * 时间范围查询条件 逗号隔开
     */
    private String searchTime;

    /**
     * 时间范围查询条件（开始时间）
     */
    @TableField(exist = false)
    private Date startTime;
    /**
     * 时间范围查询条件(结束时间)
     */
    @TableField(exist = false)
    private Date endTime;
    /**
     * 次数 操作符
     */
    @TableField(exist = false)
    private  String operator;

}
