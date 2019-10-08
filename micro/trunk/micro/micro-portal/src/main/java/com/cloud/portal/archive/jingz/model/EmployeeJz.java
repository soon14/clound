package com.cloud.portal.archive.jingz.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author wengshij
 * @date Created in 2019/6/13 19:44
 * @description: 警综从业人员信息
 * @modified By:wengshij
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeJz extends Model<EmployeeJz> {
    /**
     * 保安员类别
     */
    private String baylb;
    /**
     * 保安资格等级
     */
    private String bazgdj;
    /**
     * 保安资格证编号
     */
    private String bazgzbh;
    /**
     * 备注
     */
    private String bz;
    /**
     * 初次领资格证时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cclzgzsj;
    /**
     * 初次领证日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cclzrq;
    /**
     * 持枪证号
     */
    private String cqzh;
    /***/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;
    /***/
    private String creator;
    /**
     * 出生日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date csrq;
    /**
     * 档案编号
     */
    private String dabh;
    /***/
    private String deleteFlag;
    /**
     * 部门代码
     * */
    private String departmentCode;
    /**
     * 登记单位名称
     */
    private String djdw;
    /**
     * 登记单位代码
     */
    private String djdwdm;
    /**
     * 登记人姓名
     */
    private String djr;
    /**
     * 登记人警号
     */
    private String djrjh;
    /**
     * 登记时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date djsj;
    /**
     * 地级以上市公安机关审核人
     */
    private String djyssgajgshr;
    /**
     * 地级以上市公安机关审核日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date djyssgajgshrq;
    /**
     * 地级以上市公安机关审核意见
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date djyssgajgshyj;
    /**
     * 地级以上市审核公安机关
     */
    private String djyssshgajg;
    /***/
    private String downloadflag;
    /**
     * 地市
     */
    private String ds;
    /**
     * 单位编码
     */
    private String dwbh;
    /**
     * 单位名称
     */
    private String dwmc;
    /**
     * 兵役状况
     */
    private String fbyqk;
    /**
     * 服务单位
     */
    private String fwdw;
    /**
     * 发证机关
     */
    private String fzjg;
    /**
     * 发证日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date fzrq;
    /**
     * 国籍（地区）
     */
    private String gj;
    /**
     * 工作地所属警务区代码
     */
    private String gzdjwqdm;
    /**
     * 工作地所属警务区名称
     */
    private String gzdjwqmc;
    /**
     * 工作地所属派出所机构代码
     */
    private String gzdpcsdm;
    /**
     * 工作地所属派出所名称
     */
    private String gzdpcsmc;
    /**
     * 工作地省市县
     */
    private String gzdqh;
    /**
     * 工作地区县名称
     */
    private String gzdqhmc;
    /**
     * 工作地所属社区居委会/村委会代码
     */
    private String gzdsqdm;
    /**
     * 工作地所属社区居委会/村委会名称
     */
    private String gzdsqmc;
    /**
     * 岗位编号
     */
    private String gzgwbh;
    /**
     * 岗位名称
     */
    private String gzgwmc;
    /**
     * 岗位责任描述
     */
    private String gzgwms;
    /**
     * 工作管辖范围
     */
    private String gzgxfw;
    /**
     * 户籍地警务区
     */
    private String hjdjwq;
    /**
     * 户籍地警务区名称
     */
    private String hjdjwqmc;
    /**
     * 户籍地派出所
     */
    private String hjdpcs;
    /**
     * 户籍地派出所名称
     */
    private String hjdpcsmc;
    /**
     * 户籍地省市县（区）
     */
    private String hjdqh;
    /**
     * 户籍地省市县（区名称
     */
    private String hjdqhmc;
    /**
     * 户籍地社区
     */
    private String hjdsq;
    /**
     * 户籍地社区名称
     */
    private String hjdsqmc;
    /**
     * 详址
     */
    private String hjdxz;
    /**
     * 婚姻状况
     */
    private String hyzk;
    /**
     * 警察证号
     */
    private String jczh;
    /**
     * 单位组织机构代码
     */
    private String jgdm;
    /**
     * 紧急联系人
     */
    private String jjlxr;
    /**
     * 紧急联系人电话
     */
    private String jjlxrdh;
    /**
     * 健康状况
     */
    private String jkzk;
    /**
     * 驾驶证号
     */
    private String jszh;
    /**
     * 家庭电话
     */
    private String jtdh;
    /**
     * 外国人就业许可证编号
     */
    private String jyxkzbh;
    /**
     * 居住证编号
     */
    private String jzzbh;
    /**
     * 从事保卫工作时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date kscsbwgzsj;
    /**
     * 最后更新人
     */
    private String lastUpdatedBy;
    /**
     * 最后更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastUpdatedTime;
    /**
     * 离岗日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date lgrq;
    /**
     * 离岗原因
     */
    private String lgyy;
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
     * 备注
     */
    private String nbbz;
    /**
     * 培训情况
     */
    private String pxqk;
    /**
     * 刷新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date refreshTime;
    /**
     * 当人员编号用
     */
    private String reservation01;
    /***/
    private String reservation02;
    /**
     * 人员类别
     */
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
    /***/
    private String reservation17;
    /***/
    private String reservation18;
    /***/
    private String reservation19;
    /***/
    private String reservation20;
    /**
     * 辖区单位编号ZADWBH
     */
    private String reservation21;
    /***/
    private String reservation22;
    /***/
    private String reservation23;
    /***/
    private String reservation24;
    /***/
    private String reservation25;
    /***/
    private String reservation26;
    /***/
    private String reservation27;
    /***/
    private String reservation28;
    /***/
    private String reservation29;
    /***/
    private String reservation30;
    /***/
    private String reservation31;
    /***/
    private String reservation32;
    /***/
    private String reservation33;
    /***/
    private String reservation34;
    /***/
    private String reservation35;
    /***/
    private Date reservation36;
    /***/
    private Date reservation37;
    /***/
    private Date reservation38;
    /***/
    private Date reservation39;
    /***/
    private Date reservation40;
    /**
     * 人员所属单位类别
     */
    private String rylb;
    /**
     * 人员来源
     */
    private String ryly;
    /**
     * 人员状况
     */
    private String ryzk;
    /**
     * 人员状态（1是有效，0是注销）
     */
    private String ryzt;
    /**
     * 安全等级
     * */
    private String securityGrade;
    /**
     * 掌纹采集
     */
    private String sfszw;
    /**
     * 是否有无犯罪记录证明
     */
    private String sfywfzjl;
    /**
     * 是否有资格证
     */
    private String sfyzgz;
    /**
     * 是否政审
     */
    private String sfzs;
    /**
     * 指纹采集
     */
    private String sfzw;
    /**
     * 是否专职
     */
    private String sfzz;
    /**
     * 身高
     */
    private String sg;
    /**
     * 省公安机关审批单位
     */
    private String sgajgspdw;
    /**
     * 省公安机关审批意见
     */
    private String sgajgspyj;
    /**
     * 省公安厅审批人
     */
    private String sgatspr;
    /**
     * 省公安厅审批日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date sgatsprq;
    /**
     * 上岗日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date sgrq;
    /**
     * 上岗证件编号
     */
    private String sgzjbh;
    /**
     * 社会保障卡号
     */
    private String shbzkh;
    /**
     * 手机串号
     */
    private String sjch;
    /**
     * 系统编号
     */
    private String systemId;
    /**
     * 特长
     */
    private String tc;
    /**
     * 体检日期
     */
    private Date tjrq;
    /***/
    private String uploadFlag;
    /**
     * 文化程度
     */
    private String whcd;
    /**
     * 性别
     */
    private String xb;
    /**
     * 姓名
     */
    private String xm;
    /**
     * 县（区）公安机关审核人
     */
    private String xqgajgshr;
    /**
     * 县（区）公安机关审核日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date xqgajgshrq;
    /**
     * 县（区）公安机关审核意见
     */
    private String xqgajgshyj;
    /**
     * 县（区）审核公安机关
     */
    private String xqshgajg;
    /**
     * 血型
     */
    private String xx;
    /**
     * 现在地警区
     */
    private String xzdjq;
    /**
     * 现在地警区名称
     */
    private String xzdjqmc;
    /**
     * 现在地派出所
     */
    private String xzdpcs;
    /**
     * 现在地派出所名称
     */
    private String xzdpcsmc;
    /**
     * 现在地省市县
     */
    private String xzdqh;
    /**
     * 现在地区县名称
     */
    private String xzdqhmc;
    /**
     * 现在地社区
     */
    private String xzdsq;
    /**
     * 现在地社区名称
     */
    private String xzdsqmc;
    /**
     * 现在地详址
     */
    private String xzdxz;
    /**
     * 地址编码
     */
    private String xzzbm;
    /**
     * 移动电话
     */
    private String yddh;
    /**
     * 用工性质
     */
    private String ygxz;
    /**
     * 英文名
     */
    private String ywm;
    /**
     * 英文姓
     */
    private String ywx;
    /**
     * 有效起始日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date yxqsrq;
    /**
     * 主表编号
     */
    private String zbbh;
    /**
     * 职称
     */
    private String zc;
    /**
     * 资格证号
     */
    private String zgzh;
    /**
     * 资格证注销时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date zgzzxsj;
    /**
     * 资格证注销原因
     */
    private String zgzzxyy;
    /**
     * 证件编号
     */
    private String zjbh;
    /**
     * 准驾车型
     */
    private String zjcx;
    /**
     * 证件号码
     */
    private String zjhm;
    /**
     * 宗教信仰
     */
    private String zjsy;
    /**
     * 证件有效期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date zjyxq;
    /**
     * 证件种类
     */
    private String zjzl;
    /**
     * 管理状态
     */
    private String zt;
    /**
     * 职务
     */
    private String zw;
    /**
     * 职务类别
     */
    private String zwlb;
    /**
     * 政治面貌
     */
    private String zzmm;
    /**
     * 资质证名称
     */
    private String zzzjmc;
    /**
     * 资质证种类
     */
    private String zzzjzl;

    /**
     * 人员标签类型 逗号隔开
     */
    private String tagType;




}
