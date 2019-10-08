package com.cloud.portal.judged.analyze.seal.model;

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
 * @date Created in 2019/7/9 15:15
 * @description: 同一单位印章被刻制多次
 * @modified By:wengshij
 */
@TableName("T_THCS_ANALY_KYZ_TYDWYZBDCKZ")
@Data
@EqualsAndHashCode(callSuper = true)
public class SameSealEngraveMany extends Model<SameSealEngraveMany> {

    /**
     * 批次ID
     */
    private String batchId;
    /**
     * 场所行政区划代码
     */
    private String cityCode;
    /**
     * 刻章单位管辖派出所编码
     */
    private String deptCode;
    /**
     * 刻章单位管辖派出所名称
     */
    private String deptName;
    /**
     * 刻制时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date engraveTime;
    /**
     * 主键ID
     */
    private String id;
    /**
     * 模型标识
     */
    @TableField(value = "model_UdId")
    private String modelUdId;
    /**
     * 检测时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date monitorTime;
    /**
     * 场所地址
     */
    private String placeAddress;
    /**
     * 场所单位ID
     */
    private String placeId;
    /**
     * 场所单位名称
     */
    private String placeName;
    /**
     * 场所类型
     */
    private String placeType;
    /**
     * 印章类型（单位专用章、合同专用章等）
     */
    private String sealType;
    /**
     * 印章使用单位地址
     */
    private String useUnitAddress;
    /**
     * 印章使用单位ID
     */
    private String useUnitId;
    /**
     * 印章使用单位证件号码
     */
    private String useUnitIdCard;
    /**
     * 印章使用单位法人
     */
    private String useUnitLegal;
    /**
     * 印章使用单位名称
     */
    private String useUnitName;
    /**
     * 印章使用单位联系方式
     */
    private String useUnitPhone;
    /**
     * 印章使用单位类型
     */
    private String useUnitType;

    /**
     * 刻录次数
     */
    @TableField(exist = false)
    private Integer num;
    /**
     * 按月统计（刻制月份）
     */
    @TableField(exist = false)
    private String engraveMonth;

    /**
     * 次数 操作符
     */
    @TableField(exist = false)
    private  String operator;

    /**
     * 时间范围查询条件（开始时间）
     */
    @TableField(exist = false)
    private String startTime;
    /**
     * 时间范围查询条件(结束时间)
     */
    @TableField(exist = false)
    private String endTime;


    /**
     * 权限标识信息
     */
    @JsonIgnore
    @TableField(exist = false)
    private String dataScope;
    /**
     * 印章名称
     */
    private String sealName;


}
