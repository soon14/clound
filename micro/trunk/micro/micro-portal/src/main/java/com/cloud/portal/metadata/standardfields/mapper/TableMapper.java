

package com.cloud.portal.metadata.standardfields.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.metadata.datasource.entity.RdbSource;
import com.cloud.portal.metadata.standardfields.entity.Table;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ryt
 * @date Created in 2019/4/9 15:25
 * @description: 标准字段管理 数据表mapper接口
 * @modified By:
 */
public interface TableMapper extends BaseMapper<Table> {

    /**
     * 获取所有数据源
     * @return
     */
    List<RdbSource> getAllDataSource();

    /**
     * 数据表 列表
     * @param page
     * @param table
     * @return
     */
    IPage<Table> findTablePage(IPage<Table> page, @Param("queryCondition")Table table);

    /**
     * 表预览时候去除已经添加过的数据表
     *
     * @param table
     * @return List<Table>
     */
    List<Table> findExistsTableList(@Param("queryCondition")Table table);

    /**
     * 数据表 更新
     * @param table
     * @return
     */
    boolean updateTableById(Table table);
}
