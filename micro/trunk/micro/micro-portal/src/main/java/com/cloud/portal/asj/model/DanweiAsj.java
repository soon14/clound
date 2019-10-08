package com.cloud.portal.asj.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * @author lvlinc
 * @date Created in 2019/8/2210:35
 * @description:
 * @modified By:lvlinc
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DanweiAsj extends Model<DanweiAsj> {
    /**案别/案由*/
    private String ab;
    /**办案人联系电话*/
    private String ajbarp;
    /**案件编号*/
    private String ajbh;
    /**立案人*/
    private String ajlary;
    /**案件类型*/
    private String ajlx;
    /**案件来源*/
    private String ajly;
    /**案件名称*/
    private String ajmc;
    /**所属警区*/
    private String ajssjq;
    /**案件状态*/
    private String ajstate;
    /**案件号*/
    private String ajwh;
    /**危害程度*/
    private String ajwhcd;
    /**案件协办人*/
    private String ajxbry;
    /**主要办案人*/
    private String ajzbry;
    /**备注*/
    private String bz;
    /**发案地点详址*/
    private String fadd;
    /**发案地点_街道*/
    private String faddId;
    /**发案地点_区县*/
    private String faddQx;
    /**选择地域*/
    private String fady;
    /**发案时间初值*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date fasjcz;
    /**发案时间终值*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date fasjzz;
    /**发现形式*/
    private String fxxs;
    /**结案时间*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date jaJasj;
    /**立案单位*/
    private String ladw;
    /**立案日期*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lasj;
    /**破案时间*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date pasj;
    /**接警受理号
     */
    private String slBjslh;
    /**接警方式
     */
    private String slJjfs;
    /**受理人*/
    private String slLrr;
    /**受理时间*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date slLrsj;
    /**发案处所*/
    private String slfacs;
    /**发现时间*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date slfxrq;
    /**接警单位*/
    private String sljjdw;
    /**报警时间*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date sljjsj;
    /**受理单位/接收单位*/
    private String sljsdw;
    /**受伤人数*/
    private Integer ssrs;
    /**所属社区*/
    private String sssq;
    /**死亡人数*/
    private Integer swrs;
    /**系统编号*/
    private String systemid;
    /**销案时间*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date xaXasj;
    /**作案时机*/
    private String xzsj;
    /**专案标识*/
    private String zabz;
    /**作案状态*/
    private String zazt;
    /**主办单位*/
    private String zbdw;
    /**简要案情*/
    private String zyaq;
}
