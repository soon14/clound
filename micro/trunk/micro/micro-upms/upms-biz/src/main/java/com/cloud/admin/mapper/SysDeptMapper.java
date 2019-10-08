

package com.cloud.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.admin.api.dto.DeptTree;
import com.cloud.admin.api.entity.SysDept;
import com.cloud.common.data.datascope.DataScope;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 部门管理 Mapper 接口
 * </p>
 *
 * @author ygnet
 * @since 2018-01-20
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

	/**
	 * 关联dept——relation
	 *
	 * @return 数据列表
	 */
	List<SysDept> listDepts();

	/**
	 * 权限过滤
	 * @param dataScope
	 * @return
	 */
	List<SysDept> deptList(DataScope dataScope);

	/**
	 * 异步加载树菜单
	 * @param sysDept
	 * @return 叶子节点数
	 */
	List<SysDept> asyncTree(@Param("query") SysDept sysDept);

	/**
	 * 判断是否有叶子节点
	 * @param id
	 * @return int
	 */
	int hasChildren(@Param("id")String id);

	/**
	 * 异步加载树菜单
	 * @param sysDept
	 * @return 叶子节点数
	 */
    List<SysDept> asyncTreeList(@Param("query") SysDept sysDept);
}
