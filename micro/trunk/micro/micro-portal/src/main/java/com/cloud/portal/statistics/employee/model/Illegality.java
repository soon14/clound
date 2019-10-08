package com.cloud.portal.statistics.employee.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Molly
 * @date Created in 2019/8/9
 * @description: 统计查询-统计报表-案件信息实体
 * @modified By: Molly
 * */
@Data
public class Illegality extends Model<Illegality> {

    private static final long serialVersionUID = 1L;

    /** 案件编号 */
    private String ajbh;
    /** 案件名称 */
    private String ajmc;
    /** 案别 */
    private String ab;
    /** 案件类型 */
    private String ajstate;
    /** 发案时间 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime fasjcz;
    /** 受理单位 */
    private String sljsdw;
    /** 受理单位名称 */
    private String sljsdwName;
    /** 发案地点 */
    private String fadd;
    /** 简要案情 */
    private String zyaq;
}
