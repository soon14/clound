

package com.cloud.portal.metadata.standardfields.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.metadata.standardfields.entity.Table;

import java.util.List;
import java.util.Map;


/**
 * @author ryt
 * @date Created in 2019/4/9 15:25
 * @description: 标准字段管理 数据表service
 * @modified By:
 */
public interface TableService extends IService<Table> {

    /**
     * 查询数据源树菜单
     *
     * @return 树
     */
    List<Map<String, Object>> selectTree();

    /**
     * 查询数据表
     * @param table
     * @return
     */
    IPage<Table> findTablePage(IPage<Table> page, Table table);

    /**
     * 预览数据表
     * @param table
     * @return
     */
    List<Table> getPreViewTable(Table table);

    /**
     * 保存数据表
     * @param table
     * @return
     */
    Boolean saveTable(Table table);
    /**
     * 修改数据表
     *
     * @param table 参数
     * @return Boolean
     */
    Boolean updateTableById(Table table);

    /**
     * 删除数据表
     *
     * @param id
     * @return Boolean
     */
    Boolean removeTableById(String id);
}
