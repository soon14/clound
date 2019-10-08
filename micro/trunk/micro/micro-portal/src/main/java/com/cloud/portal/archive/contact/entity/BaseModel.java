package com.cloud.portal.archive.contact.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * @author ryt
 * @date Created in 2019-06-04
 * description: 场所关联 基础类
 * modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseModel<T extends BaseModel> extends Model<T> implements Cloneable {
    /**
     * 场所单位编号
     */
    private String dwbh;
    /**
     * 场所单位名称
     */
    private String dwmc;
    /**
     * 行业类型(场所类型 -七行八所 字典)
     */
    private String hylx;
    /**
     * 企业状态
     */
    private String placeState;

    /**
     * 行政区划
     */
    private String areaCode;

    /**
     * 所属派出所
     */
    private String sspcs;

    /**
     * 场所地址
     */
    private String address;

    /**
     * 单位id，娱乐场所用到
     */
    private String compId;

    /**
     * 警综关联数据
     */
    @TableField(exist = false)
    List<Map<String,String>> selectList;

    /**
     * 权限标识SQL
     */
    @TableField(exist = false)
    private String dataScope;
}
