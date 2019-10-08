

package com.cloud.portal.metadata.standardfields.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.metadata.standardfields.entity.Field;
import com.cloud.portal.metadata.standardfields.service.FieldService;
import com.google.common.base.Splitter;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @author ryt
 * @date Created in 2019/4/9 15:25
 * @description: 标准字段管理 数据表字段controller
 * @modified By:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/field")
@Api(value = "field", description = "数据表字段")
public class FieldController {

    private final FieldService fieldService;

    /**
     * 分页查询数据表字段
     *
     * @param page    参数集
     * @param field 查询参数列表
     * @return 数据表集合
     */
    @GetMapping("/page")
    @SysLog("数据表字段查询")
    public R getFieldPage(Page page, Field field) {
        return new R<>(fieldService.findFieldPage(page,field));
    }

    /**
     * 分页查询数据源表字段
     *
     * @param page    参数集
     * @param field 查询参数列表
     * @return 数据表集合
     */
    @GetMapping("/preViewPage")
    @SysLog("数据源表查询")
    public R getPreViewFieldPage(Page page,Field field) {
        return new R<>(fieldService.preField(field));
    }

    /**
     * 通过ID查询
     *
     * @param id ID
     * @return Field
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable String id) {
        return new R<>(fieldService.getById(id));
    }

    /**
     * 添加
     *
     * @param field 实体
     * @return success/false
     */
    @SysLog("添加")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('data_table_add')")
    public R save(@Valid @RequestBody Field field) {
        return new R<>(fieldService.saveField(field));
    }

    /**
     * 预览导入
     *
     * @param field 实体
     * @return success/false
     */
    @SysLog("预览导入")
    @PostMapping("/import")
    @PreAuthorize("@pms.hasPermission('data_table_add')")
    public R saveAll(@Valid @RequestBody Field field) {
        return new R<>(fieldService.saveAllField(field));
    }

    /**
     * 删除
     *
     * @param ids id字符串拼接
     * @return success/false
     */
    @SysLog("删除数据表字段")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@pms.hasPermission('data_table_del')")
    public R removeById(@PathVariable String ids) {
        List<String> idList = Splitter.on(",").trimResults().splitToList(ids);
        return new R<>(fieldService.removeByIds(idList));
    }

    /**
     * 编辑
     *
     * @param field 实体
     * @return success/false
     */
    @SysLog("编辑数据表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('data_table_edit')")
    public R update(@Valid @RequestBody Field field) {
        return new R<>(fieldService.updateFieldById(field));
    }

    /**
     * 根据字段名查询数据表字段信息
     *
     * @param name tableId
     * @return
     */
    @GetMapping("/checkName/{name}/{tableId}")
    public R table(@PathVariable String name,@PathVariable String tableId) {
        Field condition = new Field();
        condition.setName(name);
        condition.setTableId(tableId);
        return new R<>(fieldService.getOne(new QueryWrapper<>(condition)));
    }
}
