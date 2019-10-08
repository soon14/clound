

package com.cloud.admin.api.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cloud.admin.api.entity.SysDept;
import com.cloud.admin.api.entity.SysRole;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ygnet
 * @date 2017/10/29
 */
@Data
public class UserVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private String userId;
	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 身份证号码
	 */
	private String idcard;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 警号
	 */
	private String policeNumber;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 随机盐
	 */
	private String salt;

	/**
	 * 微信openid
	 */
	private String wxOpenid;

	/**
	 * QQ openid
	 */
	private String qqOpenid;

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
	private String delFlag;

	/**
	 * 锁定标记
	 */
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
	private String deptId;



	/**
	 * 部门名称
	 */
	private String deptName;

	/**
	 * 角色信息
	 */

	private SysRole sysRole;

	/**
	 * 部门信息
	 */
	private SysDept sysDept;
}
