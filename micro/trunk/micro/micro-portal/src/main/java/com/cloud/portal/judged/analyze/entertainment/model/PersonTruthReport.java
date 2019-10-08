package com.cloud.portal.judged.analyze.entertainment.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author ryt
 * @date Created in 2019/7/9 09:41
 * @description: 从业人员未如实报备分析模型-针对娱乐场所
 * @modified By:
 */
@TableName("T_THCS_ANALY_YL_CYRYWRSBB")
@Data
@EqualsAndHashCode(callSuper = true)
public class PersonTruthReport extends Model<PersonTruthReport> {

    /**
     * 主键ID
     */
    private String id;
    /**
     * 场所名称
     */
    private String name;
    /**
     * 行政区划
     */
    private String cityCode;
    /**
     * 管辖派出所 代码
     */
    private String deptCode;
    /**
     * 管辖派出所名称
     */
    private String deptName;
    /**
     * 地址
     */
    private String address;
    /**
     * 营业状态
     */
    private String type;
    /**
     * 场所主键ID
     */
    private String placeId;
    /**
     * 实际从业人数
     */
    private Integer actuleNum;
    /**
     * 打卡人数
     */
    private Integer falseNum;

    /**
     * 模型唯一标识
     */
    @TableField(value = "MODEL_UDID")
    private String modelUdId;

    /**
     * 批次ID
     */
    private String batchId;

    /**
     * 最新打卡时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date clockTime;

    /**
     * 检测时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date monitorTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "GMT+8")
    /**出生日期*/
    private Date csrq;
    /**曾用名*/
    private String cym;
    /**国籍*/
    private String gj;
    /**户籍地址省市县(区)*/
    private String hjdzssxq;
    /**户籍地址详址*/
    private String hjdzxz;
    /**证件号码*/
    private String idCard;
    /**联系电话*/
    private String lxdh;
    /**民族*/
    private String mz;
    /**文化程度*/
    private String whcd;
    /**性别*/
    private String xb;
    /**姓名*/
    private String xm;
    /**居住地址全址*/
    private String zzdzqz;
    /**居住地址省市县（区）*/
    private String zzdzssxq;

    /**
     * 权限标识信息
     */
    @JsonIgnore
    @TableField(exist = false)
    private String dataScope;

    /**
     * 按月统计，时间范围查询条件 逗号隔开
     */
    @TableField(exist = false)
    private String clockMonth;

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


}
