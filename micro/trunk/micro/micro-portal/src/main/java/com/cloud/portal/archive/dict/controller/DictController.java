package com.cloud.portal.archive.dict.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.archive.dict.model.Dict;
import com.cloud.portal.archive.dict.service.DictService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * @author lvlinc
 * @date Created in 2019-5-27
 * @description: 字典接口
 * @modified By:lvlinc
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dict")
public class DictController {
    private final DictService dictService;

     /**
     * 分页查询数据表字段
     * @param page    参数集
     * @param dict 查询参数列表
     * @return Dict 数据表集合
     */
    @GetMapping("/page")
    @SysLog("字典列表查询")
    public R findDict (Page page, Dict dict){
        return new R<>(dictService.findDict(page,dict));
    }

    @GetMapping("/getDict/{codetype}/{lyxt}")
    @Cacheable(value = "dict_details", key = "(#codetype).concat('-').concat(#lyxt)")
    @SysLog("获取字典")
    public R getDict(@PathVariable String codetype,@PathVariable String lyxt){

        return new R<>(dictService.getDict(codetype,lyxt));
    }

    /**
     * 获取字典信息
     *
     * @param code        字典key
     * @param codetype    字典类型
     * @param lyxt        来源系统
     * @return
     */
    @GetMapping("/label/{codetype}/{code}/{lyxt}")
    public String getDictLabel(@PathVariable String codetype, @PathVariable String code, @PathVariable String lyxt) {
        return dictService.getDictLabel(codetype,code,lyxt);
    }
}
