package com.cloud.portal.event.model;

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
 * @date Created in 2019/8/28 15:59
 * @description: 事件苗头
 * @modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("T_THCS_EVENT_SIGNS")
public class EventSigns extends Model<EventSigns> {

    /**
     * 单位名称
     */
    private String dwmc;
    /**
     * 单位编号（单位id）
     */
    private String dwbh;
    /**
     * 单位地址
     */
    private String address;
    /**
     * 单位所属行政区划
     */
    private String areaCode;

    /**处理情况*/
    private String clqk;
    /**创建时间*/
    private Date createdtime;
    /**创建者*/
    private String creator;
    /**参与人数*/
    private Integer cyrs;
    /**主要参与人员类别*/
    private String cyrylb;
    /**删除标志*/
    private String deleteflag;
    /**部门编号*/
    private String departmentcode;
    /**下传标志*/
    private String downloadflag;
    /**发现时间*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date fxsj;
    /**事件苗头工作情况*/
    private String gzqk;
    /**活动范围*/
    private String hdfw;
    /**街路巷*/
    private String jlx;
    /**相关警情编号*/
    private String jqbh;
    /**简要情况*/
    private String jyqk;
    /**可能发生地点*/
    private String knfsdd;
    /**可能发生时间*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date knfssj;
    /**最终修改人*/
    private String lastupdatedby;
    /**最后修改时间*/
    private Date lastupdatedtime;
    /**领导批示、意见*/
    private String ldps;
    /**苗头类型*/
    private String mtlx;
    /**详细地址*/
    private String qzlyd;
    /**更新时间*/
    private Date refreshtime;
    /**保留字段1*/
    private String reservation01;
    /**保留字段2*/
    private String reservation02;
    /**保留字段3*/
    private Date reservation03;
    /**保留字段4*/
    private String reservation04;
    /**保留字段5*/
    private String reservation05;
    /**状态（未处理、已处理、转事件、转行政案件、转刑事案件）*/
    private String reservation06;
    /**保留字段7*/
    private String reservation07;
    /**保留字段8*/
    private String reservation08;
    /**保留字段9*/
    private String reservation09;
    /**保留字段10*/
    private String reservation10;
    /**保留字段11*/
    private String reservation11;
    /**保留字段12*/
    private String reservation12;
    /**保留字段13*/
    private String reservation13;
    /**已用做记录参与人员*/
    private String reservation14;
    /**是否上报*/
    private String reservation15;
    /**密级*/
    private String securitygrade;
    /**审核结果*/
    private String shjg;
    /**审核领导*/
    private String shld;
    /**审核时间*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date shsj;
    /**事件苗头编号*/
    private String sjmtbh;
    /**事件苗头名称*/
    private String sjmtmc;
    /**系统编号*/
    private String systemid;
    /**填表单位*/
    private String tbdw;
    /**填表人*/
    private String tbr;
    /**填表时间*/
    private Date tbsj;
    /**上传标志*/
    private String uploadflag;
    /**信息来源*/
    private String xxly;
    /**行政区划*/
    private String xzqh;
    /**原因*/
    private String yy;
    /**实际入库时间*/
    private Date zaptResCreatetime;
    /**更新时间*/
    private Date zaptResUpdatetime;
    /**组织串联情况*/
    private String zzclqk;

    /**
     * 数据权限
     */
    @TableField(exist = false)
    @JsonIgnore
    private String dataScope;
    /**
     * 查询开始时间
     */
    @TableField(exist = false)
    private String startTime;
    /**
     * 查询结束时间
     */
    @TableField(exist = false)
    private String endTime;

}
