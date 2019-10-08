package com.cloud.portal.point.scheme.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author lvlinc
 * @date Created in 2019/7/18
 * @description: 周期详细
 * @modified By:lvlinc
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Cycle extends Model<Cycle> {
    private String schemeId;
    /**周期开始时间*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date cycleStartTime;
    /**周期结束时间*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date cycleEndTime;
    private String cycle;
}
