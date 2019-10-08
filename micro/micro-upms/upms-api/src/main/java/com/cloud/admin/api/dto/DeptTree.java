

package com.cloud.admin.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ygnet
 * @date 2018/1/20
 * 部门树
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptTree extends TreeNode {
	private String name;
	private String code;
	private Integer sort;
}
