

package com.cloud.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.cloud.admin.api.annotation.LogField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 部门管理
 * </p>
 *
 * @author ygnet
 * @since 2018-01-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDept extends Model<SysDept> implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "dept_id", type = IdType.UUID)
	@LogField(title = "主键")
	private String deptId;
	/**
	 * 部门名称
	 */
	@NotBlank(message = "部门名称不能为空")
	@LogField(title = "部门名称")
	private String name;
	/**
	 * 上级部门名称
	 */
	private String upname;
	/**
	 * 部门编码
	 */
	@LogField(title = "部门编码")
	private String code;
	/**
	 * 排序
	 */
	@LogField(title = "排序")
	private Integer sort;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;
	@LogField(title = "父级主键")
	private String parentId;

	/**
	 * 是否删除  -1：已删除  0：正常
	 */
	@TableLogic
	private String delFlag;

	private String area;

	private String hasChildren;
}
