package com.cloud.portal.archive.jingz.model;

import com.cloud.portal.archive.common.base.model.DataModel;
import lombok.Data;

import java.util.Date;

/**
 * @author wengshij
 * @date Created in 2019/5/7 11:07
 * @description: 治安管辖单位（包含七行八所）
 * @modified By:wengshij
 */
@Data
public class Gxdw extends DataModel<Gxdw> {



    /**
     * 按摩床(椅)数量 -按摩
     */
    private Integer amcysl;
    /**
     * 按摩室数量 -按摩
     */
    private Integer amssl;
    /**
     * 安全员数量
     */
    private Integer aqysl;
    /**
     * 保安人员来源(字典)
     */
    private String baly;
    /**
     * 派驻单位
     */
    private String bapzdw;
    /**
     * 保安人员总数
     */
    private String baryzs;
    /**
     * 保安组织负责人姓名
     */
    private String bazzfzrxm;
    /**
     * 保安组织联系电话
     */
    private String bazzlxdf;
    /**
     * 实际经营人办公电话
     */
    private String bglxdh;
    /**
     * 被兼营单位
     */
    private String bjydw;
    /**
     * 备注
     */
    private String bz;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 创建者
     */
    private String creator;
    /***/
    private String crksl;
    /**
     * 床位数-旅业
     */
    private Integer cws;
    /**
     * 从业人员总数
     */
    private String cyryzs;
    /**
     * 删除标志
     */
    private String deleteFlag;
    /**
     * 部门编号
     */
    private String departmentCode;
    /**
     * 等级-旅业
     */
    private String dj;
    /***/
    private String downloadFlag;

    /**
     * 单位所在地派出所
     */
    private String dwszdpcs;
    /**
     * 单位所在地区
     */
    private String dwszdxzqh;
    /**
     * 单位英文名称
     */
    private String dwywmc;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 房间数-旅业
     */
    private Integer fjs;
    /**
     * 法人代表
     */
    private String frdb;
    /**
     * 服务器数量- 上网服务
     */
    private Integer fwqsl;
    /**
     * 放映厅数量 -放映厅
     */
    private Integer fytsl;
    /**
     * 管辖行业类型（七行八所单位类型）
     */
    private String gxhylx;
    /***/
    private String hdrl;
    /**
     * 行业类别
     */
    private String hylb;
    /**
     * 技防设施
     */
    private String jfss;
    /**
     * 街路巷
     */
    private String jlx;
    /**
     * 技术委托单位电话 -上网服务
     */
    private String jswtdwdh;
    /**
     * 技术委托单位地址 -上网服务
     */
    private String jswtdwdz;
    /**
     * 技术服务委托单位名称 -上网服务
     */
    private String jswtdwmc;
    /**
     * 警务区联系电话
     */
    private String jwqlxdh;
    /**
     * 经营地址
     */
    private String jydz;
    /**
     * 经营项目兼营
     */
    private String jyxmjy;
    /**
     * 经营项目主营
     */
    private String jyxmzy;
    /**
     * 经营状态
     */
    private String jyzt;
    /**
     * 经营人员数量(兼职)
     */
    private Integer jzjyrsl;
    /**
     * 开锁工艺-开锁业
     */
    private String ksgy;
    /**
     * 开业日期
     */
    private Date kyrq;
    /**
     * 刻字方式-刻字业
     */
    private String kzfs;
    /**
     * 最终修改人
     */
    private String lastUpdatedBy;
    /**
     * 最后修改时间
     */
    private Date lastUpdatedTime;
    /**
     * 楼层数-旅业
     */
    private Integer lcs;
    /**
     * 旅馆编号
     */
    private String lgbh;
    /**
     * 联系电话
     */
    private String lxdh;
    /**
     * 录入人
     */
    private String lyr;
    /**
     * 录入时间
     */
    private Date lysj;
    /**
     * 面积
     */
    private Integer mj;
    /**
     * 门楼牌
     */
    private String mlp;
    /**
     * 培训上岗人数
     */
    private Integer pxsgrs;
    /**
     * 企业实际经营名称
     */
    private String qysjjymc;
    /**
     * 企业性质
     */
    private String qyxz;
    /**
     * 企业注册名称
     */
    private String qyzcmc;
    /***/
    private Date refreshTime;
    /**
     * 注1：以被用为：注销原因
     */
    private String reservation01;
    /***/
    private String reservation02;
    /**
     * 备注:地址编码
     */
    private String reservation03;
    /**
     * 该字段标识外网生成帐号
     */
    private String reservation04;
    /**
     * 行政区划名称
     */
    private String reservation05;
    /**
     * 街路巷名称
     */
    private String reservation06;
    /**
     * 警区名称
     */
    private String reservation07;
    /**
     * 社区名称
     */
    private String reservation08;
    /**
     * 单位管辖派出所名称
     */
    private String reservation09;
    /***/
    private String reservation10;
    /**
     * 注：用为：机构组织代码
     */
    private String reservation11;
    /**
     * 乡镇（街道）代码
     */
    private String reservation12;
    /**
     * 乡镇（街道）名称
     */
    private String reservation13;
    /**
     * 治安负责人证件种类
     */
    private String reservation14;
    /**
     * 治安负责人证件号码
     */
    private String reservation15;
    /**
     * 主营 1 | 兼营 0 | 否则 空
     */
    private String reservation16;
    /**
     * 主\兼营单位
     */
    private String reservation17;
    /**
     * 法定代表人手机号码
     */
    private String reservation18;
    /**
     * 单位负责人手机号码
     */
    private String reservation19;
    /**
     * 治安负责人手机号码
     */
    private String reservation20;
    /***/
    private String reservation21;
    /***/
    private String reservation22;
    /***/
    private String reservation23;
    /***/
    private String reservation24;
    /**
     * 注：是否是社会信息采集用户  1为是，空或0为否
     */
    private String reservation25;
    /**
     * 消防管理员名称
     */
    private String reservation26;
    /**
     * 消防管理员联系电话
     */
    private String reservation27;
    /**
     * 消防管负责人名称
     */
    private String reservation28;
    /**
     * 消防管负责人联系电话
     */
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
    /**
     * 注1：已被用为：注销日期
     */
    private String reservation36;
    /***/
    private String reservation37;
    /***/
    private String reservation38;
    /***/
    private String reservation39;
    /***/
    private String reservation40;
    /**
     * 密级
     */
    private String securityGrade;
    /**
     * 是否记录网吧内IP地址分配情况 -上网服务
     */
    private String sfjldzfp;
    /**
     * 是否安装网络安全设备
     */
    private String sftgaqsb;
    /**
     * 是否提供上网电脑
     */
    private String sftgswdn;
    /**
     * 是否提供上网服务
     */
    private String sftgswfw;
    /**
     * 实际经营人姓名
     */
    private String sjjyrxm;
    /**
     * 上级主管部门
     */
    private String sjzgbm;
    /**
     * 所属警务区
     */
    private String ssjwq;
    /**
     * 所属社区
     */
    private String sssq;
    /***/
    private String sstdsl;
    /**
     * 上网电脑数量 -上网服务
     */
    private Integer swdnsl;
    /**
     * 上网方式 -上网服务
     */
    private String swfs;
    /**
     * 上网接口数量
     */
    private Integer swjksl;
    /**
     * IP地址(上网帐号) -上网服务
     */
    private String swzh;
    /**
     * 系统主键
     */
    private String systemId;
    /**
     * 填表人
     */
    private String tbr;
    /**
     * 填表时间
     */
    private Date tbsj;
    /***/
    private String uploadFlag;
    /**
     * 舞池核定人数 --歌舞娱乐场所
     */
    private Integer wchdrs;
    /**
     * 舞池面积 --歌舞娱乐场所
     */
    private Integer wcmj;
    /**
     * 网管负责人姓名
     */
    private String wgfzrxm;
    /**
     * 网管负责人联系电话
     */
    private String wglxdh;
    /**
     * 网管软件版本 -上网服务
     */
    private String wgrjbb;
    /**
     * 网管软件开发单位 -上网服务
     */
    private String wgrjkfdw;
    /**
     * 网管软件名称 -上网服务
     */
    private String wgrjmc;
    /**
     * 网管负责人身份证号码
     */
    private String wgzjhm;
    /**
     * 网络接入提供商-上网服务
     */
    private String wljrtgs;
    /**
     * 防审核单位
     */
    private String xfshdw;
    /**
     * 消防审核情况
     */
    private String xfshqk;
    /**
     * 厢房数量-歌舞娱乐场所
     */
    private Integer xfsl;
    /**
     * 星级-旅业
     */
    private String xj;
    /**
     * 详细地址
     */
    private String xxdz;
    /**
     * 实际经营人手机
     */
    private String ydlxdh;
    /**
     * 印刷机机型-印刷业
     */
    private String ysjjx;
    /**
     * 印刷机数量-印刷业
     */
    private Integer ysjsl;
    /**
     * 游戏游艺机数 -游戏厅
     */
    private Integer yxyyjs;
    /**
     * 治安单位编号
     */
    private String zadwbh;
    /**
     * 单位编号
     */
    private String zbdwbh;
    /**
     * 注册地址
     */
    private String zcdz;
    /**
     * 注册资金(万元)
     */
    private Float zczj;
    /**
     * 总核定人数 -歌舞娱乐场所
     */
    private Integer zhdrs;
    /***/
    private String zjxx;
    /**
     * 责任民警警号
     */
    private String zrmjjh;
    /**
     * 责任民警姓名(治安负责人姓名)
     */
    private String zrmjxm;
    /**
     * 座位数 -放映厅
     */
    private Integer zws;
    /**
     * 组织机构代码
     */
    private String zzjgdm;
    /**
     * 经营人员数量(专职)
     */
    private Integer zzjyrsl;

}
