package com.cloud.portal.archive.jingz.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author ryt
 * @date Created in 2019/6/18 16:14
 * @description: 警综消防监督检查
 * @modified By:
 */
@TableName("THGL_RES.jz_b_za_rcxfjdxx")
@Data
@EqualsAndHashCode(callSuper = true)
public class Xfjc extends Model<Xfjc> {

    /**
     * 备注
     */
    private String bz;
    /**
     * 创建时间
     */
    private LocalDateTime createdtime;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 50平米以上场所安全出口不少于2个，疏散门宽度不小于1.4米，出口处不得设置门槛、台阶，不得锁闭，
     * 不得采用卷帘门，转门，门应向外开启，防火门应有自闭功能。疏散门上方应设‘安全出口’、‘禁止锁闭’标志,kind='00'
     */
    private String csaqck;
    /**
     * 删除标志
     */
    private String deleteflag;
    /**
     * 下传标志
     */
    private String downloadflag;
    /**
     * 电气设备、开关、插座不得安装在可燃材料上，电器设施周围不得堆放可燃物质,kind='00'
     */
    private String dqsbsfkr;
    /**
     * 单位编号
     */
    private String dwbh;
    /**
     * 单位地址
     */
    private String dwdz;
    /**
     * 单位名称
     */
    private String dwmc;
    /**
     * 单位性质,kind='za_001'
     */
    private String dwxz;
    /**
     * 单位主管人员（签名）
     */
    private String dwzgry;
    /**
     * (村委)多种形式消防组织,kind='XF_016'
     */
    private String dzxsxfzz;
    /**
     * (村委)防火安全公约,kind='XF_003'
     */
    private String fhaqgy;
    /**
     * 防火检查,kind='XF_004'
     */
    private String fhjc;
    /**
     * 防火门情况,kind='XF_008'
     */
    private String fhmqk;
    /**
     * 公众聚集场所安全检查,kind='XF_002'
     */
    private String gzjjcsaqjc;
    /**
     * 公众聚集场所是否违反规定使用明火作业,kind='00'
     */
    private String gzjjcsmhzy;
    /**
     * 检查编号
     */
    private String jcbh;
    /**
     * 检查代号
     */
    private String jcdh;
    /**
     * 检查日期
     */
    private LocalDate jcrq;
    /**
     * 监督检查人员（签名）
     */
    private String jdjcry;
    /**
     * 建筑层数
     */
    private Integer jzcs;
    /**
     * 建筑防火其他情况
     */
    private String jzfhqtqk;
    /**
     * 建筑高度
     */
    private Integer jzgd;
    /**
     * 建筑面积
     */
    private Integer jzmj;
    /**
     * 建筑物合法性
     */
    private String jzwhfs;
    /**
     * 建筑物消防设施,kind='XF_012'
     */
    private String jzwxfss;
    /**
     * 建筑物消防验收,kind='XF_001'
     */
    private String jzwxfys;
    /**
     * 最后修改人
     */
    private String lastupdatedby;
    /**
     * 最后修改时间
     */
    private LocalDateTime lastupdatedtime;
    /**
     * 灭火和应急疏散预案,kind='XF_003'
     */
    private String mhhyjssya;
    /**
     * 灭火器,kind='XF_011'
     */
    private String mhq;
    /**
     * 灭火器按100平米4具的要求齐备，应便于取用且不影响疏散，灭火器摆放稳固，
     * 铭牌须朝外，并定期点检，灭火器箱不得上锁,kind='00'
     */
    private String mhqaqzk;
    /**
     * 部门编号
     */
    private String reservation01;
    /**
     * 检查是否合格,kind='XF_020'
     */
    private String reservation02;
    /**
     * 是否归档,kind='XF_021'
     */
    private String reservation03;
    /**
     * 检查类型,kind='XF_022'
     */
    private String reservation04;
    /**
     * 警情编号
     */
    private String reservation05;
    /**
     * 字号
     */
    private String reservation06;
    /**
     * 创建人警号
     */
    private String reservation07;
    /**
     * 最后修改人警号
     */
    private String reservation08;
    /**
     * 被查建筑物名称
     */
    private String reservation09;
    /**
     * 被查建筑物其他情况
     */
    private String reservation10;
    /**
     * 是否公众聚集场所,kind='XF_019'
     */
    private String reservation11;
    /**
     * 是否依法通过投入使用、营业前消防安全检查,kind='XF_019'
     */
    private String reservation12;
    /**
     * 安全出口,kind='XF_0071'
     */
    private String reservation13;
    /**
     * 人员密集场所外墙门窗上是否设置影响逃生、灭火救援的障碍物,kind='XF_023'
     */
    private String reservation14;
    /**
     * 是否存在违反规定使用明火作业或在具有火灾、爆炸危险 的场所吸烟、使用明火,kind='XF_023'
     */
    private String reservation15;
    /**
     * 是否存在违反消防安全规定进入生产、储存易燃易爆危险品场所,kind='XF_023'
     */
    private String reservation16;
    /**
     * 生产、储存、经营易燃易爆危险品的场所是否与居住场所设置在同一建筑物内,kind='XF_005'
     */
    private String reservation17;
    /**
     * 所属社区居村委代码
     */
    private String reservation18;
    /**
     * 数据来源，01内网、02移动端
     */
    private String reservation19;
    /**
     * 处理结果：kind='zajcxx_cljg'(无用)
     */
    private String reservation20;
    /**
     * 处理方式：,kind='zajcxx_clfs'
     */
    private String reservation21;
    /**
     * 处理建议：,kind='zajcxx_cljy'
     */
    private String reservation22;
    /**
     * 线索线报ID
     */
    private String reservation23;
    /**
     * 所属警务区
     */
    private String reservation24;
    /**
     * 系统唯一编号
     */
    private String reservation25;
    /**
     * 归档时间
     */
    private LocalDateTime reservation26;
    /***/
    private LocalDateTime reservation27;
    /**
     * 设备IMEI码
     */
    private String reservation28;
    /**
     * 移动端最后修改人
     */
    private String reservation29;
    /**
     * 移动端最后修改时间
     */
    private LocalDateTime reservation30;
    /**
     * 住宿与生产存储经营等用途不得混合设置在同一连通空间,kind='00'
     */
    private String sfhhsz;
    /**
     * 室内消防通道宽度不小于1米，应布置合理并保存畅通，不得堵塞或占用，通道醒目处位设置‘禁止阻塞’标志,kind='00'
     */
    private String snxftd;
    /**
     * 室内消火栓,kind='XF_010'
     */
    private String snxhs;
    /**
     * 疏散和安全通道,kind='XF_007'
     */
    private String sshaqtd;
    /**
     * 疏散指示标志,kind='XF_009'
     */
    private String sszsbz;
    /**
     * 系统编号
     */
    private String systemid;
    /**
     * 上传标志
     */
    private String uploadflag;
    /**
     * 违规用火用电用气，经营场所电器线路敷设未穿阻燃线管保护，用火线入口端没有安装漏电开关,kind='00'
     */
    private String wgyhydyq;
    /**
     * 危险品与居住场所一致,kind='XF_023'
     */
    private String wxpyjzcsyz;
    /**
     * (村委)消防安全工作制度,kind='XF_003'
     */
    private String xfangzzd;
    /**
     * (村委)消防安全管理人,kind='XF_013'
     */
    private String xfaqglr;
    /**
     * 消防安全检查不合格文号
     */
    private String xfaqjcbhgwh;
    /**
     * 消防安全检查合格文号
     */
    private String xfaqjchgwh;
    /**
     * 消防安全其他情况,kind='XF_019'
     */
    private String xfaqqtqk;
    /**
     * 消防安全制度,kind='XF_003'
     */
    private String xfaqzd;
    /**
     * 消防车通道,kind='XF_006'
     */
    private String xfctd;
    /**
     * 消防竣工备案号
     */
    private String xfjgbah;
    /**
     * 消防控制室是否有人值班，人员是否持有相关证件,kind='00'
     */
    private String xfkzs;
    /**
     * 消防设施维护管理,kind='XF_024'
     */
    private String xfsswhgl;
    /**
     * (村委)消防水源、消防车通道、消防器材,kind='XF_015'
     */
    private String xfsy;
    /**
     * (村委)消防宣传教育,kind='XF_014'
     */
    private String xfxcjy;
    /**
     * 消防演练情况
     */
    private String xfylqk;
    /**
     * 消防验收文号
     */
    private String xfyswh;
    /**
     * 员工消防安全教育培训,kind='XF_004'
     */
    private String ygxfaqjypx;
    /**
     * 应急照明,kind='XF_009'
     */
    private String yjzm;
    /**
     * 发现安全违法行为，移送公安机关消防机构依法处理
     */
    private String ysgajgclqk;
    /**
     * 移送消防机构项
     */
    private String ysxfjgx;
    /**
     * 移送消防机构处理内容,kind='XF_017'
     */
    private String ysxfjgxlnr;
    /**
     * (村委)防火安全检查,kind='XF_014'
     */
    private String yzy;
    /**
     * 制发的法律文书名称和编号
     */
    private String zfflwsmchbh;
    /**
     * 责令改正编号
     */
    private String zlgzbh;
    /**
     * 中心经度
     */
    private String zxjd;
    /**
     * 墙面、吊顶、分隔装修不得大量使用可燃材料，分隔墙到顶,kind='00'
     */
    private String zxsykrcl;
    /**
     * 中心纬度
     */
    private String zxwd;
    /**
     * 主要负责人
     */
    private String zyfzr;

    /**
     * 消防检查 开始结束日期时间
     */
    @TableField(exist = false)
    private String startTime;
    @TableField(exist = false)
    private String endTime;
}
