

package com.cloud.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.cloud.admin.api.annotation.LogField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author ygnet
 * @since 2017-11-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDict extends Model<SysDict> {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId(value = "id", type = IdType.UUID)
	@LogField(title = "主键")
	private String id;
	/**
	 * 数据值
	 */
	@NotBlank(message = "字典项数据值不能为空")
	@LogField(title = "数据值")
	private String value;
	/**
	 * 标签名
	 */
	@NotBlank(message = "字典项标签不能为空")
	@LogField(title = "标签名")
	private String label;
	/**
	 * 类型
	 */
	@NotBlank(message = "字典项数据类型不能为空")
	@LogField(title = "类型",dictType = "DICTTYPE")
	private String type;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 排序（升序）
	 */
	private Integer sort;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 备注信息
	 */
	private String remarks;
	/**
	 * 删除标记
	 */
	@TableLogic
	private String delFlag;

	/** 父Id */
	private String parentId;

	/** 层级父Id 使用,分割*/
	private String parentIds;

	/**
	 * 创建人id
	 */
	private String createBy;

	/**
	 * 修改人id
	 */
	private String updateBy;

	@TableField(exist = false)
	private String isTree;
}
