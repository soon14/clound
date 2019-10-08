package com.cloud.portal.archive.mechanical.model;

import com.cloud.portal.archive.common.base.model.DataModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
/**
 * @author lvlinc
 * @date Created in 2019/5/9 17:34
 * @description: 机修从业人员
 * @modified By:lvlinc
 */
@Data
public class Cyry extends DataModel<Cyry> {
    /**单位编码*/
    private String zagldwbm;
    /**单位名称*/
    private String dwmc;
    /**姓名*/
    private String xm;
    /**性别代码*/
    private String xbdm;
    /**民族*/
    private String mzdm;
    /**证件种类*/
    private String cyzjdm;
    /**证件号码*/
    private String zjhm;
    /**现住地址编码*/
    private String dzmcdzbm;
    /**省市县（区）*/
    private String dzmcssxqdm;
    /**区划内详细地址*/
    private String dzmcqhnxxdz;
    /**学历*/
    private String xldm;
    /**国籍*/
    private String gjdm;
    /**户籍地址地址编码*/
    private String hjdzdzbm;
    /**省市县(区)代码    */
    private String hjdzssxqdm;
    /**区划内详细地址*/
    private String hjdzqhnxxdz;
    /**工种名称*/
    private String gzmc;
    /**入职日期*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime rzrq;
    /**离职日期*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime lzrq;
    /**数据归属单位名称*/
    private String sjgsdwmc;
    /**数据归属单位代码*/
    private String sjgsdwdm;
    /**登记时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime djsj;
    /**更新时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gxsj;
    private String xxid;
    private String sjgxsm;
    private boolean xh;
    private String xh1;
    /**在岗人数*/
    private String zgrs;
    /**本月上岗*/
    private String bysg;
    /**本月离岗*/
    private String bylg;
    private byte[] zp;
    private String startTime;
    private String endTime;
    /**
     * 人员标签类型 逗号隔开
     */
    private String tagType;
}
