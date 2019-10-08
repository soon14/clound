package com.cloud.portal.judged.model.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import oracle.sql.DATE;

import java.util.Date;

/**
 * @author lvlinc
 * @date Created in 2019/7/1
 * @description:模型收藏
 * @modified By:lvlinc
 */
@Data
@TableName("T_THCS_MENU_MODEL_COLLECT")
@EqualsAndHashCode(callSuper = true)
public class Collect extends Model<Collect> {
    /**收藏时间*/
    private DATE collectTime;
    /**主键ID*/
    private String id;
    /**模型ID*/
    private String modelId;
    /**收藏者ID*/
    private String userId;
    /**模型名称*/
    private String name;
    /**模型唯一标识符（可作为字典）*/
    @TableField(value = "udId")
    private String udId;
    /**权限标识*/
    private String permission;
    /**图标（大字段处理）*/
    private byte[] icon;
    /**类型（标题、目录 、模型）*/
    private String type;
    /**浏览次数*/
    private Integer viewTimes;
    /**链接地址*/
    private String links;
    /**模型打开方式(字典model_open_type)*/
    private String openType;
    /**简要信息*/
    private String remark;
    /**排序*/
    private Integer sort;
    /**评价星级（5星）*/
    private Integer stars;
    /**创建时间*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String collect;
    private String isCollect;

}
