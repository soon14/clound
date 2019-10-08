package com.cloud.portal.archive.jingz.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author ryt
 * @date Created in 2019/6/15 10:44
 * @description: 警综治安检查（日常检查）信息
 * @modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Zajc extends Model<Zajc> {

    /***/
    private String systemid;
    /**
     * 日常检查编号
     */
    private String rcjcbh;
    /**
     * 被检查单位
     */
    private String bjcdw;
    /**
     * 备注
     */
    private String bz;
    /**
     * 处理结果
     */
    private String cljg;
    /**
     * 处理意见
     */
    private String clyj;
    /***/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdtime;
    /***/
    private String creator;
    /***/
    private String deleteflag;
    /***/
    private String departmentcode;
    /***/
    private String downloadflag;
    /**
     * 当前等级
     */
    private String dqdj;
    /**
     * 当前分数
     */
    private Integer dqfs;
    /**
     * 单位编号
     */
    private String dwbh;
    /**
     * 单位类别
     */
    private String dwlb;
    /**
     * 单位类型
     */
    private String dwlx;
    /**
     * 单位名称
     */
    private String dwmc;
    /**
     * 发现问题
     */
    private String fzwt;
    /**
     * 管辖行业类型
     */
    private String gxhylx;
    /**
     * 检查单位
     */
    private String jcdw;
    /**
     * 检查方式
     */
    private String jcfs;
    /**
     * 检查人
     */
    private String jcr;
    /**
     * 检查时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime jcsj;
    /***/
    private String jcxmxx;
    /**
     * 检查意见
     */
    private String jcyj;
    /**
     * 扣分分数
     */
    private Integer kffs;
    /**
     * 扣分情况
     */
    private String kfqk;
    /***/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String lastupdatedby;
    /***/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastupdatedtime;
    /***/
    private Integer lrjls;
    /**
     * 落实情况
     */
    private String lsqk;
    /***/
    private String lyr;
    /***/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lysj;
    /**
     * 其他
     */
    private String qt;
    /**
     * 其他检查内容
     */
    private String qtjcnr;
    /**
     * 企业当值负责人
     */
    private String qydzfzr;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    /***/ private LocalDateTime refreshtime;
    /***/
    private String reservation01;
    /***/
    private String reservation02;
    /***/
    private String reservation03;
    /***/
    private String reservation04;
    /***/
    private String reservation05;
    /***/
    private String reservation06;
    /***/
    private String reservation07;
    /***/
    private String reservation08;
    /***/
    private String reservation09;
    /***/
    private String reservation10;
    /***/
    private String reservation11;
    /***/
    private String reservation12;
    /***/
    private String reservation13;
    /***/
    private String reservation14;
    /***/
    private String reservation15;
    /***/
    private String reservation16;
    /**
     * 辖区单位编号ZADWBH
     */
    private String reservation17;
    /***/
    private String reservation18;
    /**
     * 保留字段19
     */
    private String reservation19;
    /***/
    private String reservation20;
    /***/
    private String securitygrade;
    /***/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sjzgrq;
    /***/
    private String uploadflag;
    /**
     * 限期整改日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime xqzgrq;
    /**
     * 对应表B_ZA_ZAXQDW的字段DWBM
     */
    private String zadwbh;
    /**
     * 对应主表B_DW的单位编号
     */
    private String zbdwbh;
    /**
     * 整改落实截止日期 字段
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime zglsjzrq;
    /**
     * 整改落实开始日期 字段
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime zglsksrq;
    /**
     * 治安检查 开始结束日期时间
     */
    @TableField(exist = false)
    private String startTime;
    @TableField(exist = false)
    private String endTime;
}
