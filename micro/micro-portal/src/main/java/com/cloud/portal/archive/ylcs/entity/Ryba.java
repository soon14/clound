package com.cloud.portal.archive.ylcs.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author ryt
 * @date Created in 2019/5/9 17:34
 * @description: 娱乐场所-人员备案
 * @modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Ryba extends Model<Ryba> {
    private static final long serialVersionUID = 1L;
    /**
     * 变更登记时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bgDjsj;
    /**
     * 变更节点
     */
    private String bgNotpass;
    /**
     * 变更状态
     */
    private Integer bgState;
    /**
     * 变更通过时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bgTgsj;
    /**公司区域代码*/
    private String compCode;
    /**公司Id*/
    private String compId;
    /**公司名称*/
    private String compName;
    /**场所备案编号*/
    private String csbabh;
    /**
     * 指纹数据
     */
    private byte[] fingerprintData;
    /**
     * 备案节点
     */
    private String notpass;
    /**
     * 备案(通过)日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rybaBasj;
    /**
     * 补备告知日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rybaBbgzsj;
    /**
     * 补备完成日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rybaBbwcsj;
    /**
     * 补备原因
     */
    private String rybaBbyy;
    /**
     * 从业人员编号
     */
    private String rybaBh;
    /**
     * 别名
     */
    private String rybaBieming;
    /**
     * 备注
     */
    private String rybaBz;
    /**
     * 出生日期
     */
    private LocalDate rybaCsrq;
    /**
     * 从业状态 10 在职,11 在岗,12 出差,13 请假,19 其他在职状态,20 离职,90 其他状态,99 其他
     */
    private String rybaCyzt;
    /**
     * 登记日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rybaDjsj;
    /**
     * 国籍代码 CHN 中国,QT 外国人
     */
    private String rybaGjdm;
    /**
     * 岗位编号(采用顺序码（01～99）
     */
    private String rybaGwbh;
    /**
     * 岗位名称
     */
    private String rybaGwmc;
    /**
     * 岗位责任描述
     */
    private String rybaGwzrms;
    /**
     * 更新日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rybaGxsj;
    /**
     * 户籍地址
     */
    private String rybaHjdz;
    /**
     * 户籍所在地区划代码(区划表前六位)
     */
    private String rybaHjqhdm;
    /**
     * 婚姻状况代码 10 未婚,20 已婚,21 初婚,22 再婚,23 复婚,30 丧偶,40 离婚,90 未说明的婚姻状况
     */
    private String rybaHyzkdm;
    /**
     * 主键
     */
    private String rybaId;
    /**
     * 籍贯
     */
    private String rybaJg;
    /**
     * 紧急联系人
     */
    private String rybaJjlxr;
    /**
     * 紧急联系人电话
     */
    private String rybaJjlxrdh;
    /**
     * 健康状况
     */
    private String rybaJkzk;
    /**
     * 居住地址
     */
    private String rybaJzdz;
    /**
     * 从业人员类别
     * 10 管理人员,11 法定代表人或主要负责人,12 主要股东组成人员,13 治安负责人,19 其他管理人员
     * 20 服务人员,21 一般服务人员,22 临时服务人员,23 演艺人员,29 其他服务人员
     * 30 保安人员,40 安全检查人员,99 其他从业人员
     */
    private String rybaLb;
    /**
     * 联系电话
     */
    private String rybaLxdh;
    /**
     * 离职时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rybaLzsj;
    /**
     * 从业人员姓名
     */
    private String rybaMc;
    /**民族*/
    private String rybaMz;
    /**
     * 民族代码
     */
    private String rybaMzdm;
    /**
     * 聘用期限
     */
    private Integer rybaPyqx;
    /**
     * 签发机关
     */
    private String rybaQfjg;
    /**
     * 身高（cm）
     */
    private Integer rybaSg;
    /**
     * 所属负责人
     */
    private String rybaSsfzr;
    /**
     * 所属负责人电话
     */
    private String rybaSsfzrdh;
    /**
     * 体检日期
     */
    private LocalDate rybaTjrq;
    /**
     * 文化程度 1 博士,,2 研究生,3 本科,4 大专,5 高中(中专),6 其他
     */
    private Integer rybaWhcd;
    /**
     * 性别 1男 2女
     */
    private Integer rybaXb;
    /**
     * 血型 A表示A型,B表示B型,O表示O型,AB表示AB型,其他表示其他血型
     */
    private String rybaXx;
    /**
     * 制卡状态（0：未制卡 1：申请中  2：已制卡）
     */
    private Integer rybaZhika;
    /**
     * 常用证件代码(默认身份证)
     */
    private String rybaZjdm;
    /**
     * 证件号码
     */
    private String rybaZjhm;
    /**人员照片(IDCard)*/
    private String rybaZp;
    /**人员照片(现场采集照)*/
    private String rybaZp2;
    /**人员照片(指纹照片)*/
    private String rybaZp3;
    /**
     * 注销日期
     */
    private LocalDateTime rybaZxsj;
    /**
     * 暂住证号码
     */
    private String rybaZzzhm;
    /**
     * 备案状态 默认0  -2表示该人员已经注销
     */
    private Integer state;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 外国人就业许可证编号
     */
    private String wgrxkzh;
    /**
     * 业务类型 1歌舞,2游艺,3按摩
     */
    private String ywLx;

    /**
     * 本月新上岗人员数量
     */
    @TableField(exist = false)
    private String bysg;
    /**
     * 本月离岗人员数量
     */
    @TableField(exist = false)
    private String bylg;

    /**
     * 人员身份证照片
     */
    @TableField(exist = false)
    private byte[] rybaZpContent;
}
