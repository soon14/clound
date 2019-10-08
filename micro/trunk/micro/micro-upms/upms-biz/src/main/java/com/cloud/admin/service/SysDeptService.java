

package com.cloud.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.admin.api.dto.DeptTree;
import com.cloud.admin.api.entity.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 部门管理 服务类
 * </p>
 *
 * @author ygnet
 * @since 2018-01-20
 */
public interface SysDeptService extends IService<SysDept> {
	/**
	 * 判断是否有叶子节点
	 * @param id
	 * @return int
	 */
	int hasChildren(String id);
	/**
	 * 异步加载树菜单
	 * @param sysDept
	 * @return 叶子节点数
	 */
	List<DeptTree> asyncTree(SysDept sysDept);

	/**
	 * 异步加载树菜单
	 * @param sysDept
	 * @return 叶子节点数
	 */
    List<DeptTree> asyncTreeList(SysDept sysDept);

	/**
	 * 查询部门树菜单
	 *
	 * @return 树
	 */
	List<DeptTree> selectTree();

	/**
	 * 查询用户部门树
	 *
	 * @return
	 */
	List<DeptTree> getUserTree();

	/**
	 * 添加信息部门
	 *
	 * @param sysDept
	 * @return
	 */
	Boolean saveDept(SysDept sysDept);

	/**
	 * 删除部门
	 *
	 * @param id 部门 ID
	 * @return 成功、失败
	 */
	Boolean removeDeptById(String id);

	/**
	 * 更新部门
	 *
	 * @param sysDept 部门信息
	 * @return 成功、失败
	 */
	Boolean updateDeptById(SysDept sysDept);

}
