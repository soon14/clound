package com.cloud.portal.search.scheme.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author ryt
 * @date Created in 2019/8/5 11:06
 * @description: 综合查询-查询方案配置
 * @modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("T_THCS_SEARCH_SCHEME")
public class SearchScheme extends Model<SearchScheme> {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 方案名称
     */
    @TableField(condition = SqlCondition.LIKE)
    private String name;
    /**
     * 查询方式(业务表、REST  接口)
     */
    private String searchType;
    /**
     * 接口地址或者业务表名
     */
    private String searchInput;
    /**
     * 接口用户名
     */
    private String apiUserName;
    /**
     * 接口密码
     */
    private String apiPwd;
    /**
     * 状态 字典（0-启用 1停用）
     */
    private String status;
    /**
     * 分类(字典 树形结构)
     */
    private String classification;
    /**
     * 排序
     */
    private Integer sorts;
    /**
     * 查询内容（VUE JSON 格式）
     */
    private String searchContent;
    /**
     * 说明
     */
    private String remark;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 创建时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
    /**
     * 删除标识 字典（0 正常 1已删除）
     */
    @TableLogic(value = "0", delval = "1")
    private String delFlag;

    /**
     * token请求地址
     */
    private String tokenAddress;

    /**
     * 请求方式
     */
    private String requestType;

}
