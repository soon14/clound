

package com.cloud.admin.controller;

import com.cloud.admin.api.dto.DeptTree;
import com.cloud.admin.api.entity.SysDept;
import com.cloud.admin.service.SysDeptService;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * <p>
 * 部门管理 前端控制器
 * </p>
 *
 * @author ygnet
 * @since 2018-01-20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dept")
@Api(value = "dept", description = "部门管理模块")
public class DeptController {
	private final SysDeptService sysDeptService;

	/**
	 * 通过ID查询
	 *
	 * @param id ID
	 * @return SysDept
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable String id) {
		return new R<>(sysDeptService.getById(id));
	}


	/**
	 * 返回树形菜单集合
	 *
	 * @return 树形菜单
	 */
	@GetMapping(value = "/tree")
	public R getTree() {
		return new R<>(sysDeptService.selectTree());
	}

	/**
	 * 返回树形菜单集合
	 *
	 * @return 异步加载树形菜单
	 */
	@GetMapping(value = "/asyncTree")
	public R asyncTree(SysDept sysDept) {
		return new R<>(sysDeptService.asyncTree(sysDept));
	}

	/**
	 * 返回树形集合
	 *
	 * @return 异步加载树形列表
	 */
	@GetMapping(value = "/asyncTreeList")
	public R asyncTreeList(SysDept sysDept) {
		return new R<>(sysDeptService.asyncTreeList(sysDept));
	}

	/**
	 * 返回当前用户树形菜单集合
	 *
	 * @return 树形菜单
	 */
	@GetMapping(value = "/user-tree")
	public R getUserTree() {
		return new R<>(sysDeptService.getUserTree());
	}

	/**
	 * 添加
	 *
	 * @param sysDept 实体
	 * @return success/false
	 */
	@SysLog("添加部门")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_dept_add')")
	public R save(@Valid @RequestBody SysDept sysDept) {
		return new R<>(sysDeptService.saveDept(sysDept));
	}

	/**
	 * 删除
	 *
	 * @param id ID
	 * @return success/false
	 */
	@SysLog("删除部门")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_dept_del')")
	public R removeById(@PathVariable String id) {
		return new R<>(sysDeptService.removeDeptById(id));
	}

	/**
	 * 编辑
	 *
	 * @param sysDept 实体
	 * @return success/false
	 */
	@SysLog("编辑部门")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_dept_edit')")
	public R update(@Valid @RequestBody SysDept sysDept) {
		sysDept.setUpdateTime(LocalDateTime.now());
		return new R<>(sysDeptService.updateDeptById(sysDept));
	}
}
