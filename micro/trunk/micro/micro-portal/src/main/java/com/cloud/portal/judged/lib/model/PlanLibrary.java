package com.cloud.portal.judged.lib.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;


/**
 * @author vlong
 * @date Created in 2019/06/27 23:19
 * description: 预案库基类
 * modified By: gxx
 */
@Data
@TableName("t_thcs_warn_plan_lib")
public class PlanLibrary extends Model<PlanLibrary> {
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    private String name;   //名称
    private String type;   //指令类型（人工指令、研判指定）
    private String placeType;   //适用场所类型（七行八所）
    private String cityCode;   //所属行政区划（地市级别）
    private String describe;   //预案内容描述（富文本包含格式）
    private Date createTime;   //创建时间
    private Date updateTime;   //更新时间
    private String createBy;   //创建者
    private String updateBy;   //更新者

    /**
     * 被调用模块
     * 0：指令发布
     */
    private String useBy;
    /**
     * 0-正常，1-删除
     */
    @TableLogic
    private String delFlag;   //删除标识
    private String status;   //状态（启用、停用）

    private String deptCode; //所属机构代码
    private String dataScope; //用于权限控制拼接sql
}