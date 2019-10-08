package com.cloud.portal.statistics.employee.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeMessage extends Model<EmployeeMessage> {

    private static final long serialVersionUID = 1L;
    /**唯一键值*/
    private String systemId;
    /** 姓名 */
    private String name;
    /** 性别 */
    private String xb;
    /** 出生日期 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate csrq;
    /** 民族 */
    private String mz;
    /** 证件号码 */
    private String zjhm;
    /** 户籍地 */
    private String hjqh;
    /** 居住地址 */
    private String jzdz;
    /** 从业状态 */
    private String cyzt;
    /** 上岗日期 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate sgrq;
    /** 离岗日期 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate lgrq;
    /** 单位id */
    private String dwid;
    /** 单位名称*/
    private String dwmc;
    /** 单位地址 */
    private String dwdz;
    /** 管辖单位 */
    private String gxdw;
    /** 管辖区划 */
    private String gxqh;
    /** 行业类型 */
    private String hylx;
    /** 数据来源 */
    private String sourceType;
    /** 人员类型 */
    private String tagType;
    /**
     * 权限标识SQL
     */
    @TableField(exist = false)
    private String dataScope;

}
