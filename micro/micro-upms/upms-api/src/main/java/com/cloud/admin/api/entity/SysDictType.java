package com.cloud.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wuxx
 * @date 2019/2/19 16:44
 * @description: 字典类型Entity
 * @modified By:
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDictType extends Model<SysDict> {

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;

	/**
	 * 内部标识符
	 */
	@NotBlank(message = "内部标识符不能为空")
	private String innerFlag;
	/**
	 * 中文名称
	 */
	@Length(min=0, max=64, message="中文名称长度必须介于 0 和 64 之间")
	private String chineseName;
	/**
	 * 中文全拼
	 */
	private String fullName;
	/**
	 * 标识符(字段名称)
	 */
	private String name;
	/**
	 * 版本
	 */
	private String versions;

	/**
	 * 同义名
	 */
	private String synonymy;
	/**
	 * 说明
	 */
	@TableField(value="`explain`")
	private String explain;
	/**
	 * 对象类词
	 */
	private String objectWord;
	/**
	 * 特性词
	 */
	private String characterWord;
	/**
	 * 表示词
	 */
	private String expressWord;
	/**
	 * 表示格式
	 */
	private String dateType;
	/**
	 * 表示格式
	 */
	private String expressFormat;
	//@ExcelField(title="值域")
	/**
	 *  值域
	 */
	@TableField(value="`range`")
	private String range;
	/**
	 *  关系
	 */
	private String relation;
	/**
	 * 计量单位
	 */
	private String measurUnit;
	/**
	 *  主要起草人
	 */
	private String status;
	/**
	 *  主要起草人
	 */
	private String organ;
	/**
	 *  主要起草人
	 */
	private String rapporteur;
	/**
	 * 批准日期
	 */
	private String approveDate;

	/**
	 * 是否是树形结构
	 */
	private String isTree;
	/**
	 * 是否纳入缓存
	 */
	private String source;
	/**
	 * 是否纳入缓存
	 */
	private String cache;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;

	/**
	 * 删除标记
	 */
	@TableLogic
	private String delFlag;

	/**
	 * 创建人id
	 */
	private String createBy;

	/**
	 * 修改人id
	 */
	private String updateBy;

	@TableField(exist = false)
	private List<SysDict> dictList;

	/**
	 * 常量树形字典
	 */
	public static final String IS_TREE_YES = "1";
	/**
	 * 常量列表字典
	 */
	public static final String IS_TREE_NO = "0";
}