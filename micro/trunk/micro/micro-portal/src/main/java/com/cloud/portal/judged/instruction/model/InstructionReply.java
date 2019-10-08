package com.cloud.portal.judged.instruction.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Molly
 * @date Created in 2019/7/5
 * @description: 智能预警-预警反馈实体
 * @modified By: Molly
 */
@TableName("t_thcs_warn_instruct_reply")
@Data
public class InstructionReply extends Model<InstructionReply> {

    /**主键ID*/
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**指令ID*/
    private String instructId;
    /**反馈民警警号*/
    private String policeNo;
    /**反馈内容*/
    private String remark;
    /**反馈单位*/
    private String replyOrg;
    /**反馈时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime replyTime;
    /**反馈单位名*/
    private String replyOrgLabel;
    /**反馈民警姓名*/
    private String policeName;
}
