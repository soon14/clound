package com.cloud.portal.archive.ylcs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author ryt
 * @date Created in 2019/5/20 10:00
 * @description: 娱乐场所-保安巡检
 * @modified By:
 */
@Data
@TableName("THGL_RES.YL_BAXJ")
@EqualsAndHashCode(callSuper = true)
public class Baxj extends Model<Baxj> {
    private static final long serialVersionUID = 1L;
    /**主键*/
    private String baxjId;
    /**巡检人*/
    private String baxjXjr;
    /**巡检开始时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime baxjKssj;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    /**巡检结束时间*/
    private LocalDateTime baxjJssj;
    /**巡检数*/
    private Integer baxjXjs;
    /**漏检数*/
    private Integer baxjLjs;
    /**巡检人主键*/
    private String baxjXjrid;
    /**巡检单位*/
    private String compName;
    /**单位ID*/
    private String compId;
    /**单位code*/
    private String compCode;
    /**场所编号*/
    private String csbaBh;
    /**巡检线路ID*/
    private String xjlxId;
    /**巡检线路名称*/
    private String xjlxName;
    /**备注*/
    private String baxjBz;
    /**创建者*/
    private String creator;
    /**编辑者*/
    private String editMan;
    /**创建时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**编辑时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime editTime;
    /**业务类型 1歌舞,2游艺,3按摩*/
    private String ywLx;
}
