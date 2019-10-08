

package com.cloud.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.admin.api.entity.SysDept;
import com.cloud.common.data.datascope.DataScope;
import io.lettuce.core.dynamic.annotation.Param;

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
}
