package com.cloud.portal.archive.kyzy.model;

import com.cloud.portal.archive.common.base.model.DataModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author hyx
 * @date Created in 2019/6/17 17:05
 * @description: 刻印章业-印章表
 * @modified By:
 */
@Data
public class SealInfo extends DataModel<SealInfo> {

    private static final long serialVersionUID = 1L;

    /**【add：新增；update：修改】*/
    private String action;
    /**申请时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime applyDate;
    /**审批时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime auditDate;
    /**企业编号*/
    private String bizNum;
    /**印章中心图案*/
    private String centerDesign;
    /**检验时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime checkDate;
    /**芯片id*/
    private String chipId;
    /***/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createAt;
    /**创建时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;
    /**创建用户*/
    private String createUserId;
    /**注册时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createdAt;
    /***/
    private LocalDate date;
    /**交付时间*/
    private LocalDate deliverDate;
    /**失效时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime disableDate;
    /**失效类型,6缴销 7挂失 5报废*/
    private String disableType;
    /**印章边宽*/
    private int edgeWidth;
    /**印章竖长(高)*/
    private int height;
    /**印章编码*/
    private String id;
    /**检验标志(1检验合格，2检验不合格,其它未检验）*/
    private String inspectMark;
    /**检验说明*/
    private String inspectRemark;
    /**映射标记*/
    private String isMap;
    /** 原料 */
    private String material;
    /**印章名称*/
    private String name;
    /**代加工标记*/
    private String processSign;
    /**制作时间*/
    private LocalDate produceDate;
    /**生产传输标记*/
    private String produceTransSign;
    /**制作单位编码*/
    private String produceUnitId;
    /**印章形状*/
    private String shape;
    /**印章状态*/
    private String status;
    /**同步标志*/
    private String syncFlag;
    /**印章文字对照(中英文对照)*/
    private String textComparison;
    /**印章类型*/
    private String type;
    /**承接时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime undertakeDate;
    /**申请承接单位编码*/
    private String undertakeUnitId;
    /**修改时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTime;
    /**修改用户*/
    private String updateUserId;
    /***/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updatedAt;
    /**印章使用单位编码*/
    private String useUnitId;
    /**印章横长(宽)*/
    private int width;
    /**使用单位名称*/
    private String approveUnitName;
    /**使用单位法定负责人*/
    private String approveUnitLegalName;
    /** 使用单位地址 */
    private String approveUnitAddress;

    /** 查询条件 **/
    /**开始时间*/
    private String startTime;
    /**结束时间*/
    private String endTime;
}
