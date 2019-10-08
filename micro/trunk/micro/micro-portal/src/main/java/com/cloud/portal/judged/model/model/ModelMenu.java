package com.cloud.portal.judged.model.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author ryt
 * @date Created in 2019/6/21 16:52
 * @description: 研判模型配置
 * @modified By:
 */
@Data
@TableName("T_THCS_MENU_MODEL")
@EqualsAndHashCode(callSuper = true)
public class ModelMenu extends Model<ModelMenu> {

    private static final long serialVersionUID = 1L;

    /**主键ID*/
    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    /**创建者*/
    private String createBy;
    /**创建时间*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    /**删除标识(0正常，1删除)*/
    @TableLogic
    private String delFlag;
    /**图标（大字段处理）*/
    private byte[] icon;
    /**链接地址*/
    private String links;
    /**模型名称*/
    private String name;
    /**模型打开方式(字典model_open_type)*/
    private String openType;
    /**父级ID*/
    private String parentId;
    /**父级ID集合（逗号隔开）*/
    private String parentIds;
    /**权限标识*/
    private String permission;
    /**预案库ID集合*/
    private String planLibId;
    /**简要信息*/
    private String remark;
    /**显示或隐藏 字典是否*/
    private String showHide;
    /**排序*/
    private Integer sort;
    /**评价星级（5星）*/
    private Integer stars;
    /**类型（标题、目录 、模型）*/
    private String type;
    /**模型唯一标识符（可作为字典）*/
    @TableField(value = "udId")
    private String udId;
    /**更新者*/
    private String updateBy;
    /**更新时间*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
    /**推荐使用 字典是否*/
    private String useBest;
    /**浏览次数*/
    private Integer viewTimes;
    /** 分级*/
    @TableField(exist = false)
    private String level;
    /**创建时间 研判模型展示用*/
    @TableField(exist = false)
    private String createDate;
    /**评分次数*/
    private String num;
    /**模型状态（0启用、1建设中）仅用于模型跳转控制*/
    private String state;
}
