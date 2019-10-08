package com.cloud.portal.archive.mechanical.model;

import com.cloud.portal.archive.common.base.model.DataModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
/**
 * @author lvlinc
 * @date Created in 2019/5/9 17:34
 * @description: 机修业企业信息
 * @modified By:lvlinc
 */
@Data
public class Jxyxt extends DataModel<Jxyxt> {
    /**单位编码*/
    private String zagldwbm;
    /**单位名称*/
    private String dwmc;
    /**企业状态代码*/
    private String qyztdm;
    /**单位地址地址编码*/
    private String dwdzdzbm;
    /**省市县（区）*/
    private String dwdzssxqdm;
    /**区划内详细地址*/
    private String dwdzqhnxxdz;
    /**中心点横坐标*/
    private boolean dwdzzxdhzb;
    /**中心点纵坐标*/
    private boolean dwdzzxdzzb;
    /**登记单位公安机关机构代码*/
    private String djdwgajgjgdm;
    /**公安机关名称*/
    private String djdwgajgmc;
    /**联系电话*/
    private String lxdh;
    /**注册日期*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime  zcrq;
    /**开业日期*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime kyrq;
    /**经济类型*/
    private String jjlxdm;
    /**法人和其他组织统一社会信用代码*/
    private String zzjgdm;
    /**法定代表人姓名*/
    private String fddbrxm;
    /**证件种类*/
    private String fddbrcyzjdm;
    /**证件号码*/
    private String fddbrzjhm;
    /**外文姓*/
    private String fddbrwwx;
    /**外文名*/
    private String fddbrwwm;
    /**联系电话*/
    private String fddbrlxdh;
    /**单位负责人姓名*/
    private String dwfzrxm	;
    /**证件种类*/
    private String dwfzrcyzjdm;
    /**证件号码*/
    private String dwfzrzjhm	;
    /**联系电话*/
    private String dwfzrlxdh	;
    /**保卫负责人公民身份号码*/
    private String bwfzrgmsfhm	;
    /**保卫负责人姓名*/
    private String bwfzrxm	;
    /**保卫负责人联系电话*/
    private String bwfzrlxdh;
    /**经营范围(主营)  */
    private String jyfwzy;
    /**注册资本*/
    private int zczb	;
    /**经营场地_面积（平方米）*/
    private int jycdmjpfm;
    /**行业许可证号*/
    private String hyxkzh;
    /**行业许可证发证机关名称*/
    private String fzjghyxkzfzjgmc	;
    /**营业执照号*/
    private String yyzzh;
    /**营业执照发证机关名称*/
    private String fzjgyyzhfzjgmc;
    /**招牌名*/
    private String zpm;
    /**企业类别代码*/
    private String dwlbdm;
    /**企业等级代码*/
    private String dwdj	;
    /**重点单位标识*/
    private String zddwbs;
    /**数据归属单位名称*/
    private String sjgsdwmc;
    /**数据归属单位代码*/
    private String sjgsdwdm;
    /**登记人_姓名*/
    private String djrxm;
    /**登记时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime djsj;
    /**更新时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime  gxsj;
    private String xxid;
    private String sjgxsm;
    private boolean xh;


}
