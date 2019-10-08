package com.cloud.portal.archive.common.base.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.cloud.admin.api.annotation.LogField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

/**
 * @author wengshij
 * @date Created in 2019-05-05
 * description: 智能档案场所基础类
 * modified By:
 */
@Data
@ApiModel(value = "智能档案统一实体类")
@EqualsAndHashCode(callSuper = true)
public class DataModel<T extends DataModel> extends Model<T> implements Cloneable {
    /**
     * 场所单位编号
     */
    @ApiModelProperty("场所单位编号")
    protected String dwbh;
    /**
     * 场所单位名称
     */
    @LogField(title = "单位名称")
    @ApiModelProperty("场所单位名称")
    protected String dwmc;
    /**
     * 行业类型(场所类型 -七行八所 字典)
     */
    @ApiModelProperty("行业类型-七行八所 字典")
    protected String placeType;


    /**
     * 所属辖区机构代码(12位)
     * 查询时用作参数 地市 或区县 代码
     */
    @LogField(title = "所属辖区机构代码")
    @ApiModelProperty("所属辖区机构代码")
    protected String orgCode;
    /**
     * 权限控制代码
     */
    @ApiModelProperty("权限控制代码")
    @JsonIgnore
    protected String dataScope;
    /**
     * 场所分类菜单ID
     */
    protected String menuId;
    /**
     * 企业状态
     */
    protected String placeState;

    /**
     * 特行场所 行业类型 多选查询
     */
    @JsonIgnore
    protected List<String> placeTypeList;


    /***
     * 场所积分 分数
     */
    protected String pointScore;

    /**
     * 积分等级类型 （字典代码）
     */
    @LogField(title = "积分等级类型")
    @ApiModelProperty("积分等级类型")
    protected String pointType;
    /**
     * 积分等级ID
     */
    protected String pointLevelId;
    /**
     * 积分 周期子表ID
     */
    protected String pointCycleId;
    /**
     * 积分最新评定时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date judgeTime;


}
