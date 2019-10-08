package com.cloud.portal.archive.lvye.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wengshij
 * @date Created in 2019/5/22 9:11
 * @description: 旅馆业从业人员信息 实体类
 * @modified By:wengshij
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends Model<Employee> {
    /**
     * 别名
     */
    private String bm;
    /**
     * 备注
     */
    private String bz;
    /**
     *行政区划代码
     */

    private String city;
    /**
     * 出生日期（格式：YYYYMMDD）
     */
    private String csrq;
    /**
     * 旅馆编号
     */
    private String dwbh;
    /**
     * 国籍（地区）
     */
    private String gj;
    /**
     * 工作岗位
     */
    private String gzgw;
    /**
     * 户籍地区号，省市县（区）
     */
    private String hjdqh;
    /**
     * 户籍地详细地址
     */
    private String hjdxz;
    /**
     * 婚姻状况
     */
    private String hyzk;
    /**
     * 紧急联系人
     */
    private String jjlxr;
    /**
     * 紧急联系人电话
     */
    private String jjlxrdh;
    /**
     * 居住证
     */
    private String jzz;
    /**
     * 居住证有效期
     */
    private String jzzyxq;
    /**
     * 离岗日期（格式：YYYYMMDD）
     */
    private String lgrq;
    /**
     * 离岗原因
     */
    private String lgyy;
    /**
     * 登记人
     */
    private String lrr;
    /**
     * 登记时间
     */
    private String lrsj;
    /**
     * 联系方式1
     */
    private String lxfs1;
    /**
     * 联系方式2
     */
    private String lxfs2;
    /**
     * 联系方式3
     */
    private String lxfs3;
    /**
     * 民族
     */
    private String mz;
    /**
     * 照片
     */
    private byte[] picture;
    /**
     * 从业人员编号,规则旅馆编号+5位序号
     */
    private String psid;
    /**
     * 人员类别
     */
    private String rylb;
    /**
     * 从业状态
     */
    private String ryzt;
    /**
     * 身高
     */
    private String sg;
    /**
     * 上岗日期（格式：YYYYMMDD）
     */
    private String sgrq;
    /**
     * 上岗证件编号
     */
    private String sgzjbh;
    /**
     * 所属单位类别
     */
    private String ssdwlb;
    /**
     * 文化程度
     */
    private String whcd;
    /**
     * 性别
     */
    private String xb;
    /**
     * 外国人就业许可证编号
     */
    private String xkzbh;
    /**
     * 姓名
     */
    private String xm;
    /**
     * 血型
     */
    private String xx;
    /**
     * 现住址编码
     */
    private String xzzbm;
    /**
     * 现住址区号，省市县（区）
     */
    private String xzzqh;
    /**
     * 现住址详细地址
     */
    private String xzzxz;
    /**
     * 英文名
     */
    private String ywm;
    /**
     * 英文姓
     */
    private String ywx;

    /**
     * 证件号码
     */
    private String zjhm;
    /***/
    private String zjstatus;
    /**
     * 证件种类
     */
    private String zjzl;
    /**
     * 职务
     */
    private String zw;
    /**
     * 政治面貌
     */
    private String zzmm;
    /**
     * 离职状态
     */
    private String quitType;
    /**
     * 人员标签类型 逗号隔开
     */
    private String tagType;


}
