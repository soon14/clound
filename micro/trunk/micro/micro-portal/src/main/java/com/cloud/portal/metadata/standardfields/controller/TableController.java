

package com.cloud.portal.metadata.standardfields.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.metadata.standardfields.entity.Table;
import com.cloud.portal.metadata.standardfields.service.TableService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @author ryt
 * @date Created in 2019/4/9 15:25
 * @description: 标准字段管理 数据表controller
 * @modified By:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/table")
@Api(value = "table", description = "数据表")
public class TableController {

    private final TableService tableService;

    /**
     * 返回树形菜单集合
     *
     * @return 树形菜单
     */
    @ResponseBody
    @GetMapping(value = "/tree")
    public R getTree() {
    return new R<>(tableService.selectTree());
    }

    /**
     * 分页查询数据表
     *
     * @param page    参数集
     * @param table 查询参数列表
     * @return 数据表集合
     */
    @GetMapping("/page")
    @SysLog("数据表查询")
    public R getTablePage(Page page, Table table) {
        return new R<>(tableService.findTablePage(page,table));
    }

    /**
     * 分页查询数据源表
     *
     * @param page    参数集
     * @param table 查询参数列表
     * @return 数据表集合
     */
    @GetMapping("/preViewPage")
    @SysLog("数据源表查询")
    public R getPreViewTablePage(Page page,Table table) {
        return new R<>(tableService.getPreViewTable(table));
    }

    /**
     * 通过ID查询
     *
     * @param id ID
     * @return SysDept
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable String id) {
        return new R<>(tableService.getById(id));
    }

    /**
     * 删除
     *
     * @param id ID
     * @return success/false
     */
    @SysLog("删除数据表")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('data_table_del')")
    public R removeById(@PathVariable String id) {
        return new R<>(tableService.removeTableById(id));
    }

    /**
     * 编辑
     *
     * @param table 实体
     * @return success/false
     */
    @SysLog("编辑数据表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('data_table_edit')")
    public R update(@Valid @RequestBody Table table) {
        return new R<>(tableService.updateTableById(table));
    }

    /**
     * 添加
     *
     * @param table
     * @return success/false
     */
    @SysLog("预览导入")
    @PostMapping("/import")
    @PreAuthorize("@pms.hasPermission('data_table_add')")
    public R save(@Valid @RequestBody Table table) {
        return new R<>(tableService.saveTable(table));
    }

    /**
     * 根据表名查询数据表信息
     *
     * @param name ,dbId
     * @return
     */
    @GetMapping("/checkName/{name}/{dbId}")
    public R checkTableName(@PathVariable String name,@PathVariable String dbId) {
        Table condition = new Table();
        condition.setName(name);
        condition.setDbId(dbId);
        return new R<>(tableService.getOne(new QueryWrapper<>(condition)));
    }

}
