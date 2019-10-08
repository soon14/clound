package com.cloud.portal.archive.kyzy.model;

import com.cloud.portal.archive.common.base.model.DataModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author hyx
 * @date Created in 2019/6/17 17:05
 * @description: 刻印章业-单位表
 * @modified By:
 */
@Data
public class ProduceUnit extends DataModel<ProduceUnit> {

    private static final long serialVersionUID = 1L;

    /** 单位id */
    private String id;

    /** 行政区划代码 */
    private String areaCode;

    /** 审批单位代码 */
    private String approveCode;

    /** 审批单位名称 */
    private String approveCodeName;

    /** 制作单位机构名称 */
    private String name;

    /** 单位类型(01:创业工厂,02:刻章企业,03:创业加急点,04:加工中心) */
    private String type;

    /** 门店类型：（0表示合作门店，1表示普通门店） */
    private String shopType;

    /** 法人姓名 */
    private String legalName;

    /** 法人证件类型 */
    private String idcardType;

    /** 法人证件号 */
    private String idcardNumber;

    /** 单位地址(实际经营地址) */
    private String address;

    /** 营业执照编号 */
    private String businessLicenseNumber;

    /** 组织机构代码 */
    private String organizationCode;

    /** 税务登记证号 */
    private String taxNumber;

    /** 统一社会信用代码 */
    private String uniformSocialCreditCode;

    /** 特行证号 */
    private String specialNumber;

    /** 电话 */
    private String phone;

    /** 照片 */
    private String picture;

    /** 备注 */
    private String remark;

    /** 发证日期 */
    private LocalDate issuingDate;

    /** 初次发证日期 */
    private LocalDate initIssuingDate;

    /** 有效起始日期 */
    private LocalDate startDate;

    /** 有效截止日期 */
    private LocalDate endDate;

    /** 代加工标记(a:创业加急点,0,1不能用，2-9深圳老系统各区,加工中心单位编码),新:默认是0，加工中心编码 */
    private String processSign;

    /** 电子印章0:启用,1禁用 */
    private String eseal;

    /** 状态0:启用,1禁用 */
    private String status;

    /** 省份 */
    private String provinceId;

    /** 城市 */
    private String cityId;

    /** 连锁品牌名称 */
    private String chainbrandName;

    /** 连锁品牌编号 */
    private String chainbrandId;

    /** 连锁品牌logo */
    private String chainbrandLogo;

    /** 纬度 */
    private String latitude;

    /** 经度 */
    private String longitude;

    /** 创建人 */
    private String createUserId;

    /** 创建时间 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;

    /** 更新人 */
    private String updateUserId;

    /** 更新时间 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTime;

    /** 印章系统安装情况（1:未安装,2:已安装） */
    private String sealSystemInstallState;

    /** 企业状态(0:注销,1:装机营业,2:装机停业) */
    private String enterpriseState;

    /** 责任民警姓名 */
    private String policeName;

    /** 责任民警电话 */
    private String policePhone;

    /** 治安级别(A级场所：1,B级场所:2,C级场所:3) */
    private String placeLevel;

    /** 法人手机号码 */
    private String legalPersonPhone;

    /** 企业负责人 */
    private String enterprisePrincipal;

    /** 企业负责人身份证号码 */
    private String enterprisePrincipalId;

    /** 企业负责人手机 */
    private String enterprisePrincipalPhone;

    /** 招牌名称 */
    private String signboardName;

    /** 成立日期 */
    private LocalDate registerDate;

    /** 企业性质(01:内资,02:业主委员会,03:个体,04:三来一补,05:外资,06:其它) */
    private String property;

    /** 注册资本(万元) */
    private String registerCapital;

    /** 执照地址 */
    private String licenseAddress;

    /** 经营范围(主营) */
    private String operatingScope;

    /** 经营项目(兼营) */
    private String operatingProject;

    /** 经营楼层 */
    private String operatingFloor;

    /** 经营面积(㎡) */
    private String operatingArea;

    /** 企业总人数 */
    private String enterprisePeopleNum;

    /** 灭火器(个) */
    private String fireExtinguisher;

    /** 喷淋头(个) */
    private String sprayHeader;

    /** 监控探头(个) */
    private String monitorCamera;

    /** 监控保存时间(天) */
    private String monitorSaveTime;

    /** 保安负责人 */
    private String securityChief;

    /** 保安负责人手机号码 */
    private String securityChiefPhone;

    /** 保安员(人) */
    private String securityPeople;

    /** 所属保安单位 */
    private String securityCompany;

    /** 是否创业印章(0:否,1:是) */
    private String isCyyz;

    /** 印章店开章价格() */
    private double price;

    /** 同步标志 */
    private String syncFlag;

    /** 企业编号 */
    private String bizNum;
}

