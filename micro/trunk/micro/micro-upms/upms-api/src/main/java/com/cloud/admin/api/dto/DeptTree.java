

package com.cloud.admin.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author ygnet
 * @date 2018/1/20
 * 部门树
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptTree extends TreeNode implements Serializable {
	private String name;
	private String upName;
	private String code;
	private Integer sort;
	private boolean open;
	private String hasChildren;
}
