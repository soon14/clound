package com.cloud.portal.archive.ylcs.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author ryt
 * @date Created in 2019/5/21 14:58
 * @description: 娱乐场所-公安日常检查 检查项内容
 * @modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AqjcContent extends Model<AqjcContent> {
    private static final long serialVersionUID = 1L;

    /**主键*/
    private String id;
    /**主表ID*/
    private String aqjcId;
    /**检查项ID*/
    private String proId;
    /**检查项名称*/
    private String proName;
    /**本检查项的排序号 与检查项的排序值保持一致,方便页面展示*/
    private Integer proOrderby;

    /**业务类型1歌舞,2游艺,3按摩,主要是为了减少连表查询的情况*/
    private Integer leixing;
    /**没有通过时填写的说明备注*/
    private String remark;
    /**分区字段,写入记录的时间*/
    private LocalDateTime reqDate;
    /**0否,1是*/
    private String state;
}
