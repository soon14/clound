

package com.cloud.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.cloud.admin.api.annotation.LogField;
import com.cloud.common.core.constant.CommonConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author ygnet
 * @since 2017-10-29
 */
@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "user_id", type = IdType.UUID)
    private String userId;
    /**
     * 用户名
     */
    @LogField(title = "用户名")
    private String username;

    private String password;

    /**
     * 身份证号码
     */
    @LogField(title = "身份证")
    private String idcard;
    /**
     * 性别
     */
    @LogField(title = "性别",dictType = "sex")
    private String sex;
    /**
     * 警号
     */
    @LogField(title = "警号")
    private String policeNumber;
    /**
     * 随机盐
     */
    @JsonIgnore
    private String salt;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
    /**
     * 0-正常，1-删除
     */
    @TableLogic
    private String delFlag;

    /**
     * 锁定标记
     */
    @LogField(title = "锁住")
    private String lockFlag;

    /**
     * 简介
     */
    private String phone;
    /**
     * 头像
     */
    private String avatar;

    /**
     * 部门ID
     */
    @LogField(title = "部门ID")
    private String deptId;


    /**
     * 微信openid
     */
    private String wxOpenid;

    /**
     * QQ openid
     */
    private String qqOpenid;

    @TableField(exist = false)
    private String dataScope;

    /**
     * 判断是否为超级管理员
     * @return
     */
    public boolean isAdmin(){
        return this.userId != null && CommonConstants.ADMIN_FLAG.equals(this.userId);
    }




}
