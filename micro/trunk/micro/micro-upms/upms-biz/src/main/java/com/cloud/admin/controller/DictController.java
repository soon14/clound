

package com.cloud.admin.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.api.dto.DictTree;
import com.cloud.admin.api.entity.SysDict;
import com.cloud.admin.api.entity.SysDictType;
import com.cloud.admin.api.vo.TreeUtil;
import com.cloud.admin.service.SysDictService;
import com.cloud.admin.util.DictUtils;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.common.security.util.SecurityUtils;
import com.google.common.base.Splitter;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author ygnet
 * @since 2017-11-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dict")
@Api(value = "dict", description = "字典管理模块")
public class DictController {
    private final SysDictService sysDictService;

    private CacheManager cacheManager;

    /**
     * 通过ID查询字典信息
     *
     * @param id ID
     * @return 字典信息
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable String id) {
        return new R<>(sysDictService.getById(id));
    }

    /**
     * 分页查询字典信息
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    @SysLog("字典查询")
    public R getDictPage(Page page, SysDict sysDict) {
        if (SysDictType.IS_TREE_YES.equals(sysDict.getIsTree())) {
            //树节点
            List<DictTree> list = TreeUtil.bulidDictTree(sysDictService.list(Wrappers.query(sysDict)), "0");
            Map<String, Object> map = new HashMap<>(1);
            map.put("records", list);
            return new R<>(map);
        } else {
            return new R<>(sysDictService.page(page, Wrappers.query(sysDict)));
        }
    }

    /**
     * 获取树形字典数据
     *
     * @return
     */
    @GetMapping("/tree")
    public R getDictTree(SysDict sysDict) {
        return new R<>(TreeUtil.bulidDictTree(
                sysDictService.list(Wrappers.query(sysDict))
                .stream()
                .sorted(Comparator.comparingInt(SysDict::getSort))
                .collect(Collectors.toList()), "0"));
    }

    /**
     * 通过字典类型查找字典
     *
     * @param type 类型
     * @return 同类型字典
     */
    @GetMapping("/type/{type}")
    @Cacheable(value = "dict_details", key = "#type")
    public R getDictByType(@PathVariable String type) {
        return new R<>(sysDictService.list(Wrappers
                .<SysDict>query().lambda()
                .eq(SysDict::getType, type))
                .stream()
                .sorted(Comparator.comparingInt(SysDict::getSort))
                .collect(Collectors.toList()));
    }

    /**
     * 添加字典
     *
     * @param sysDict 字典信息
     * @return success、false
     */
    @SysLog("添加字典")
    @PostMapping
    @CacheEvict(value = "dict_details", key = "#sysDict.type")
    @PreAuthorize("@pms.hasPermission('sys_dict_add')")
    public R save(@Valid @RequestBody SysDict sysDict) {
        sysDict.setCreateBy(SecurityUtils.getUser().getId());
        return new R<>(sysDictService.save(sysDict));
    }

    /**
     * 删除字典，并且清除字典缓存
     *
     * @param ids id字符串拼接
     * @return R
     */
    @SysLog("删除字典")
    @DeleteMapping("/{ids}/{type}/{isTree}")
    @CacheEvict(value = "dict_details", key = "#type")
    @PreAuthorize("@pms.hasPermission('sys_dict_del')")
    public R removeById(@PathVariable String ids, @PathVariable String type, @PathVariable String isTree) {
        List<String> idList = Splitter.on(",").trimResults().splitToList(ids);
        if (SysDictType.IS_TREE_YES.equals(isTree)) {
            // 树形字典
            return new R<>(sysDictService.removeTreeById(idList.get(0)));
        } else { //列表字典
            return new R<>(sysDictService.removeByIds(idList));
        }
    }

    /**
     * 修改字典
     *
     * @param sysDict 字典信息
     * @return success/false
     */
    @PutMapping
    @SysLog("修改字典")
    @CacheEvict(value = "dict_details", key = "#sysDict.type")
    @PreAuthorize("@pms.hasPermission('sys_dict_edit')")
    public R updateById(@Valid @RequestBody SysDict sysDict) {
        sysDict.setUpdateBy(SecurityUtils.getUser().getId());
        sysDict.setUpdateTime(LocalDateTime.now());
        return new R<>(sysDictService.updateById(sysDict));
    }

    /**
     * 获取字典信息
     *
     * @param value        字典key
     * @param type         字典类型
     * @param defaultValue 默认值
     * @return
     */
    @GetMapping("/label")
    public String getDictLabel(String value, String type, String defaultValue) {
        return DictUtils.getDictLabel(value, type, defaultValue);
    }

    @GetMapping("/dictList")
    public List<SysDict> getDictList(String type) {
        return sysDictService.list(Wrappers
                .<SysDict>query().lambda()
                .eq(SysDict::getType, type));
    }
}
