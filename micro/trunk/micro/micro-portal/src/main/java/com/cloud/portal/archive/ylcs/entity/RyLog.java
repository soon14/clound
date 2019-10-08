package com.cloud.portal.archive.ylcs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author ryt
 * @date Created in 2019/5/17 16:17
 * @description: 娱乐场所-人员日志（人员考勤）
 * @modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RyLog extends Ryba {
    private static final long serialVersionUID = 1L;
    /**主键 人员考勤id*/
    private String rylogId;
    /**公司名称*/
    private String compName;
    /**公司ID*/
    private String compId;
    /**公司区域编码*/
    private String compCode;
    /**场所备案编号*/
    private String csbaBh;
    /**人员备案ID*/
    private String rybaId;
    /**人员名称*/
    private String rybaMc;
    /**人员备案编号*/
    private String rybaBh;
    /**人员备案照片（附件表ID）*/
    private String rybaZp;
    /**场所备案编号*/
    private String csbaBabh;
    /**工作地点*/
    private String rylogGzdd;
    /**打卡时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rylogDksj;
    /**打卡类型,0上班,1下班*/
    private Integer rylogDklx;
    /**岗位信息*/
    private String rylogGwxx;
    /**现场照片*/
    private String rylogZp;
    /**身份证号*/
    private String idcardNum;
    /**身份证照片*/
    private String ryIdcardZp;
    /**业务类型*/
    private String ywLx;
    /**设备唯一编码,记录设备的信息,用于查看现场照片时通过向数据库写入记录,让对应的设备来把照片的base64编码反馈回来*/
    private String shebei;
    /**上下班状态,1上班迟到,2下班早退,0为正常*/
    private int sxbState;
}
