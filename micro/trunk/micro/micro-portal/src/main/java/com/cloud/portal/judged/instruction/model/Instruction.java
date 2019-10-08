package com.cloud.portal.judged.instruction.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Molly
 * @date Created in 2019/6/24
 * @description: 智能预警-指令实体类
 * @modified By: Molly
 */
@TableName("t_thcs_warn_instruct")
@Data
public class Instruction extends Model<Instruction> {

    private static final long serialVersionUID = 1L;

    public static final String ARTIFICIAL_INSTRUCTION_TYPE = "2";  //人工指令

    public static final String AUTOMATIC_INSTRUCTION_TYPE = "1";  //自动指令

    public static final String DRAFT_INSTRUCTION_STATUS ="0";  //草稿

    public static final String SEND_SUCCESS_INSTRUCTION_STATUS ="1";  //下发成功

    public static final String SEND_FAIL_INSTRUCTION_STATUS ="2";  //下发失败

    public static final String PROCESSED_INSTRUCTION_STATUS ="3";  //已处理

    /**批次ID*/
    private String batchId;
    /**所属行政区划*/
    private String cityCode;
    /**创建者（适用人工指令）*/
    private String createBy;
    /**创建者姓名*/
    private String createByName;
    /**主键ID*/
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**模型唯一标识ID*/
    private String modelUdid;
    /**预案表主键ID集合（逗号隔开）*/
    private String planLibId;
    /**接收单位(部门代码)*/
    private String receiveOrg;
    /**指令内容*/
    private String remark;
    /**发送者单位（部门代码）*/
    private String sendOrg;
    /**发送时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime sendTime;
    /**下发方式（社区警务、短信）*/
    private String sendType;
    /**指令状态 字典（草稿、下发成功、下发失败、已处理）*/
    private String status;
    /**限期时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime timeLimit;
    /**指令标题*/
    private String title;
    /**指令类型 字典（人工指令、研判指令）*/
    private String type;
    /**更新者（适用人工指令）*/
    private String updaeBy;
    /**更新者姓名*/
    private String updateByName;
    /**更新时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTime;
    /**接收单位显示值*/
    @TableField(exist = false)
    private String receiveOrgLabel;
    /**发送单位显示值*/
    @TableField(exist = false)
    private String sendOrgLabel;
    @TableField(exist = false)
    private String deptCode; //所属机构代码
    @TableField(exist = false)
    private String dataScope; //用于权限控制拼接sql
    @TableField(exist = false)
    private boolean notContainDraft;  //是否包含草稿状态指令
    @TableField(exist = false)
    private String planLibraryLabel; //预案标题
    @TableField(exist = false)
    private String planLibraryRemark;  //预案内容
}
