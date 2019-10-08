package com.cloud.portal.judged.analyze.machine.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * @author lvlinc
 * @date Created in 2019/7/10
 * @description:机修 同一车辆不同车行多次修改分析
 * @modified By:lvlinc
 */
@Data
@TableName("t_thcs_analy_jx_btcsdcxg")
@EqualsAndHashCode(callSuper = true)
public class DifferentPlacesRevamp extends Model<DifferentPlacesRevamp> {
    /**场所地址*/
    private String address;
    /**批次ID*/
    private String batchId;
    /**车身颜色*/
    private String carColor;
    /**车牌号*/
    private String carNo;
    /**车辆型号（宝马 X5 等等）*/
    private String carType;
    /**行政区划*/
    private String cityCode;
    /**管辖派出所*/
    private String deptCode;
    /**发动机号码*/
    private String engineNo;
    /**主键ID*/
    private String id;
    /**机动车所有人身份证号码*/
    private String idCard;
    /**模型唯一标识*/
    private String modelUdId;
    /**检测时间*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime monitorTime;
    /**机动车所有人*/
    private String name;
    /**场所ID*/
    private String placeId;
    /**场所名称*/
    private String placeName;
    /**维修日期*/
    private String repairTime;
    /**车辆类型（卡车、小车）*/
    private String type;
    /**车行个数*/
    private String sum;
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
     * 时间范围
     */
    @TableField(exist = false)
    private String repairMonth;

}
