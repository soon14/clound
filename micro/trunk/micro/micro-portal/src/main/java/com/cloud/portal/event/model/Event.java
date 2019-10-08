package com.cloud.portal.event.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author ryt
 * @date Created in 2019/8/28 15:59
 * @description: 事件
 * @modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("T_THCS_EVENT")
public class Event extends Model<Event> {

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

    /**
     * 备注
     */
    private String bz;
    /**
     * 出动船次
     */
    private Integer cdcc;
    /**
     * 出动公安民警数
     */
    private Integer cdgamjs;
    /**
     * 出动航空器次
     */
    private Integer cdhkqc;
    /**
     * 出动机动车次
     */
    private Integer cdjdcc;
    /**
     * 出动警犬数
     */
    private Integer cdjqs;
    /**
     * 出动武警数
     */
    private Integer cdwjs;
    /**
     * 出警批示领导
     */
    private String cjpsld;
    /**
     * 出警批示时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date cjpssj;
    /**
     * 出警人员总数
     */
    private Integer cjryzs;
    /**
     * 创建时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdtime;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 持续时间
     */
    private String cxsj;
    /**
     * 参与人数
     */
    private Integer cyrs;
    /**
     * 处置级别
     */
    private String czjb;
    /**
     * 逮捕（人数）
     */
    private Integer db;
    /**
     * 督办级别
     */
    private String dbjb;
    /**
     * 删除标志
     */
    private String deleteflag;
    /**
     * 部门编号
     */
    private String departmentcode;
    /***/
    private String departmentcodeName;
    /**
     * 下传标志
     */
    private String downloadflag;
    /**
     * 罚款（人数）
     */
    private Integer fk;
    /**
     * 发生处所
     */
    private String fscs;
    /**
     * 发生地点
     */
    private String fsdd;
    /**
     * 发生地域
     */
    private String fsdy;
    /**
     * 发生时间初值
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date fssjcz;
    /**
     * 发生时间终值
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date fssjzz;
    /***/
    private String fssjzzM;
    /***/
    private String fssjzzY;
    /**
     * 活动范围
     */
    private String hdfw;
    /**
     * 警告（人数）
     */
    private Integer jg;
    /**
     * 街路巷
     */
    private String jlx;
    /**
     * 相关警情编号
     */
    private String jqbh;
    /**
     * 简要情况
     */
    private String jyqk;
    /**
     * 跨区情况
     */
    private String kqqk;
    /**
     * 最终修改人
     */
    private String lastupdatedby;
    /**
     * 最后修改时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lastupdatedtime;
    /**
     * 劳动教养（人数）
     */
    private Integer ldjy;
    /**
     * 留置盘问（人数）
     */
    private Integer lzpw;
    /**
     * 轻伤人数(合计)
     */
    private Integer qsrs;
    /**
     * 其他
     */
    private String qt;
    /**
     * 强制带离（人数）
     */
    private Integer qzdl;
    /**
     * 详细地址
     */
    private String qzlyd;
    /**
     * 强制驱散（人数）
     */
    private Integer qzqs;
    /**
     * 更新时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date refreshtime;
    /**
     * 保留字段1
     */
    private String reservation01;
    /**
     * 保留字段2
     */
    private String reservation02;
    /**
     * 保留字段3
     */
    private Date reservation03;
    /**
     * 保留字段4
     */
    private String reservation04;
    /**
     * 保留字段5
     */
    private String reservation05;
    /**
     * 保留字段6
     */
    private String reservation06;
    /**
     * 保留字段7
     */
    private String reservation07;
    /**
     * 保留字段8
     */
    private String reservation08;
    /**
     * 保留字段9
     */
    private String reservation09;
    /**
     * 保留字段10
     */
    private String reservation10;
    /**
     * 保留字段11
     */
    private String reservation11;
    /**
     * 保留字段12
     */
    private String reservation12;
    /**
     * 保留字段13
     */
    private String reservation13;
    /**
     * 已用做记录参与人员
     */
    private String reservation14;
    /**
     * 是否上报
     */
    private String reservation15;
    /**
     * 密级
     */
    private String securitygrade;
    /**
     * 审核结果
     */
    private String shjg;
    /**
     * 审核领导
     */
    private String shld;
    /**
     * 审核时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date shsj;
    /**
     * 事件编号
     */
    private String sjbh;
    /**
     * 事件类型
     */
    private String sjlx;
    /**
     * 事件名称
     */
    private String sjmc;
    /**
     * 事件终结时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date sjzjsj;
    /**
     * 事件终结原因
     */
    private String sjzjyy;
    /**
     * 事件状态
     */
    private String sjzt;
    /***/
    private String sljjdw;
    /***/
    private String sljjdwFj;
    /**
     * 诉求
     */
    private String sq;
    /**
     * 死亡人数(合计)
     */
    private Integer swrs;
    /**
     * 使用警械
     */
    private String syjx;
    /**
     * 系统编号
     */
    private String systemid;
    /**
     * 填报单位
     */
    private String tbdw;
    /***/
    private String tbdwName;
    /**
     * 填表人
     */
    private String tbr;
    /***/
    private String tbrName;
    /**
     * 填表时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date tbsj;
    /**
     * 上传标志
     */
    private String uploadflag;
    /**
     * 围观人数
     */
    private Integer wgrs;
    /**
     * 刑事拘留（人数）
     */
    private Integer xsjl;
    /**
     * 行为方式
     */
    private String xwfs;
    /***/
    private String xwfsZ;
    /**
     * 行政区划
     */
    private String xzqh;
    /**
     * 原因
     */
    private String yy;
    /**
     * 治安拘留（人数）
     */
    private Integer zajl;
    /**
     * 实际入库时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date zaptResCreatetime;
    /**
     * 更新时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date zaptResUpdatetime;
    /**
     * 重伤人数(合计)
     */
    private Integer zsrs;
    /**
     * 组织串联情况
     */
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
