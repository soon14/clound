

package com.cloud.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.cloud.admin.api.util.excel.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 日志表
 * </p>
 *
 * @author ygnet
 * @since 2017-11-20
 */
@Data
public class SysLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	/**
	 * 日志类型
	 */
	@NotBlank(message = "日志类型不能为空")
	@ExcelField(title="日志类型", align=2, sort=10, dictType = "log_type")
	private String type;
	/**
	 * 日志标题
	 */
	@NotBlank(message = "日志标题不能为空")
	@ExcelField(title="标题", align=2, sort=20)
	private String title;
	/**
	 * 创建者
	 */
	@ExcelField(title="访问者", align=2, sort=25)
	private String createBy;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 操作IP地址
	 */
	@ExcelField(title="地址", align=2, sort=30)
	private String remoteAddr;
	/**
	 * 用户代理
	 */
	private String userAgent;
	/**
	 * 请求URI
	 */
	@ExcelField(title="请求地址", align=2, sort=40)
	private String requestUri;
	/**
	 * 操作方式
	 */
	@ExcelField(title="请求方法", align=2, sort=50)
	private String method;
	/**
	 * 操作提交的数据
	 */
	private String params;
	/**
	 * 执行时间
	 */
	private Long time;

	/**
	 * 异常信息
	 */
	private String exception;

	/**
	 * 服务ID
	 */
	private String serviceId;

	/**
	 * 删除标记
	 */
	@TableLogic
	private String delFlag;
	@TableField(exist = false)
	private SysUser sysUser;

}
