package com.cloud.portal.archive.ylcs.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cloud.portal.archive.common.base.model.DataModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ryt
 * @date Created in 2019/5/9 17:34
 * @description: 娱乐场所-场所备案
 * @modified By:
 */
@Data
public class Csba extends DataModel<Csba> {
    private static final long serialVersionUID = 1L;
    /***/
    private String csbaId;
    /***/
    private Integer amcsPdbz;
    /**变更登记时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bgDengjishijian;
    /**变更节点*/
    private String bgNotpass;
    /**变更状态*/
    private Integer bgState;
    /**变更通过时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bgTongguoshijian;
    /**区域编码*/
    private String compCode;
    /**公司ID */
    private String compId;
    /**公司名称*/
    private String compName;
    /***/
    private Integer compcodexl;

    /**娱乐服务场所备案编号 由一位英文字母和13位数字组成*/
    private String csbaBabh;

    /**补备告知日期*/
    private LocalDateTime csbaBbgzrq;
    /**补备完成日期*/
    private LocalDateTime csbaBbwcrq;
    /**补备原因*/
    private String csbaBbyy;
    /**包厢数量*/
    private Integer csbaBxsl;
    /**备注*/
    private String csbaBz;
    /**传真*/
    private String csbaCz;
    /**备案登记时间*/
    private LocalDateTime csbaDengjishijian;
    /**备案登记人姓名*/
    private String csbaDjr;
    /**备案登记人证件号*/
    private String csbaDjrzj;
    /**法定代表人姓名*/
    private String csbaFddbr;
    /**法定代表人证件号*/
    private String csbaFddbrzj;
    /**国家和地区代码*/
    private String csbaGddm;
    /**股东信息*/
    private String csbaGdxx;
    /**核定消费者数量*/
    private Integer csbaHdxfzsl;
    /**场所经度*/
    private Integer csbaJd;
    /**备案进度*/
    private Integer csbaJindu;
    /**经济类型（from 数据字典）*/
    private String csbaJjlx;
    /**经济类型代码（from 数据字典）*/
    private String csbaJjlxdm;
    /**经营地址*/
    private String csbaJydz;
    /**企业实际经营名称*/
    private String csbaJymc;
    /**经营面积*/
    private Integer csbaJymj;
    /**境外投资者名称*/
    private String csbaJytzz;
    /**开业日期*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime csbaKyrq;
    /**联系电话*/
    private String csbaLxdh;
    /**签证签注种类代码*/
    private String csbaQzzl;
    /**上级主管部门*/
    private String csbaSjzgbm;
    /**娱乐服务场所状态代码*/
    private String csbaState;
    /**备案通过时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime csbaTongguoshijian;
    /**备案更新时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime csbaUpdatetime;
    /**场所纬度*/
    private Integer csbaWd;
    /**娱乐经营许可证发证机构名称*/
    private String csbaXkzfzjg;
    /***/
    private String csbaXzdz;
    /**(境外投资人)娱乐经营许可证编号*/
    private String csbaYljyxkz;
    /**娱乐项目内容[经营范围与之相同]*/
    private String csbaYlnr;
    /**营业执照*/
    private String csbaYyzz;
    /**邮政编码*/
    private String csbaYzbm;
    /**营业执照发证机构名称*/
    private String csbaYzfzjg;
    /**营业执照发证机构代码*/
    private String csbaYzfzjgdm;
    /**治安管理机构名称*/
    private String csbaZagljg;
    /**治安管理机构代码*/
    private String csbaZagljgdm;
    /**治安级别代码*/
    private String csbaZajbdm;

    /**企业注册名称*/
    private String csbaZcmc;

    /**有效证件种类代码*/
    private String csbaZjdm;
    /**责任民警姓名*/
    private String csbaZrmj;

    /**企业组织机构代码(符合GB 11714的规定)*/
    private String csbaZzjgdm;

    /***/
    private Integer gwcsPdbz;
    /**备案节点*/
    private String notpass;
    /**备案状态*/
    private Integer state;
    /**版本*/
    private Integer version;
    /***/
    private String ywLx;
    /***/
    private Integer yycsPdbz;

    /**场所 管辖派出所*/
    @TableField(exist = false)
    private String sspcs;

}
