package com.cloud.portal.archive.lvye.model;

import com.cloud.portal.archive.common.base.model.DataModel;
import lombok.Data;

/**
 * @author wengshij
 * @date Created in 2019/5/14 9:30
 * @description: 旅馆基本信息实体类
 * @modified By:wengshij
 */
@Data
public class Hotel extends DataModel<Hotel> {

    /**
     * 床位数
     */
    private Integer bedNum;
    /**
     * 数据加密
     */
    private String dbEncrypt;
    /**
     * 标记位
     */
    private String flag;
    /**
     * 床位数
     */
    private String floors;
    /**
     * 等级
     */
    private String grade;
    /**
     * 地址
     */
    private String haddress;
    /**
     * 旅馆名称
     */
    private String hname;
    /**
     * 旅馆编号
     */
    private String hnoHotel;
    /**
     * 旅馆类别
     */
    private String hotelKind;
    /**
     * 旅馆前台类型
     */
    private String hotelType;
    /**
     * 旅馆前台版本
     */
    private String hotelVer;
    /**
     * 后台入库时间
     */
    private String inputTime;
    /**
     * 法人
     */
    private String legalPerson;
    /**
     * 联机时间
     */
    private String linkDate;
    /**
     * 负责人
     */
    private String principal;
    /**
     * 实际所属派出所代码
     */
    private String psCode;
    /**
     * 组织机构代码
     */
    private String psorgan;
    /**
     * 其它类型
     */
    private String qtlx;
    /**
     * 备注
     */
    private String remark;
    /**
     * 房间数
     */
    private Integer roomNum;
    /**
     * 保安部电话
     */
    private String safeTel;
    /**
     * 治安负责人
     */
    private String socialDuty;
    /**
     * 星级
     */
    private String stars;
    /**
     * 状态
     */
    private String state;
    /**
     * 状态改变日期
     */
    private String stateDate;
    /**
     * 辖区派出所
     * 格式：地市代码/区县代码/派出所代码
     */
    private String station;
    /***
     *管辖派出所翻译
     * 格式：地市名称/区县/派出所名称
     */

    private String stationEx;
    /**
     * 联系电话
     */
    private String telPhone;
    /**
     * 前台第三方软件名称
     */
    private String thirdPart;
    /**
     * 上传方式
     */
    private String transType;
    /**
     * 验证码
     */
    private String validateCode;

    private String cityCode;


}
