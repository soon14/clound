package com.cloud.portal.statistics.rectification.model;

import com.cloud.admin.api.dto.TreeNode;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author Molly
 * @date Created in 2019/8/20
 * @description: 统计查询-统计报表-隐患数据整改实体
 * @modified By: Molly
 */
@Data
public class DataRectification extends TreeNode {

    private static final long serialVersionUID = 1L;
    /** 行政区划id */
    private String id;
    /** 上级id */
    private String parentId;
    /** 区域 */
    private String code;
    /** 行业类型 */
    private String hylx;
    /** 总任务数 */
    private Integer total;
    /** 已完成任务数 */
    private Integer completed;
    /** 及时完成任务数 */
    private Integer OnTime;
    /** 完成率 */
    private Integer completedPercent;
    /** 及时率 */
    private Integer onTimePercent;
    /** 发送日期 */
    private String sendTime;
    /**查询字段*/
    private String starTime;
    private String endTime;
}
