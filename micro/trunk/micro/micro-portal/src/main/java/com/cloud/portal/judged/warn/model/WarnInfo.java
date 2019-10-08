package com.cloud.portal.judged.warn.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.cloud.portal.judged.model.model.ModelMenu;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author wengshij
 * @date Created in 2019/6/25 9:49
 * @description: 异常警告信息
 * @modified By:wengshij
 */
@Data
@TableName("T_THCS_WARNING")
@EqualsAndHashCode(callSuper = true)
public class WarnInfo extends Model<WarnInfo> {

    /**
     * 批次ID（清洗表均有一个批次ID）
     */
    private String batchId;
    /**
     * 异常简要
     */
    private String brief;
    /**
     * 异常详情
     */
    private String detail;
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 模型唯一标识符
     */
    @TableField(value = "model_udid")
    private String modelUdId;
    /**
     * 特行场所ID
     */
    private String placeId;
    /**
     * 场所类型（七行八所）
     */
    private String placeType;
    /**
     * 来源系统（警综、旅业、机修、娱乐场所）
     */
    private String sourceType;
    /**
     * 标题
     */
    private String title;
    /**
     * 发生时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date warnTime;
    /**
     * 模型信息
     */
    @TableField(exist = false)
    private ModelMenu modelMenu;

    /**
     * 警告类型（预警警告 或者自动积分扣分警告、）
     * 预留字段（可能是两张不同表合并信息）
     */
    @TableField(exist = false)
    private String type;

}
