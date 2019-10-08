package com.cloud.portal.judged.model.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.judged.model.model.Collect;
import com.cloud.portal.judged.model.service.CollectService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lvlinc
 * @date Created in 2019/7/2
 * @description:模型收藏
 * @modified By:lvlinc
 */
@RestController
@AllArgsConstructor
@RequestMapping("collect")
public class CollectController {
private CollectService collectService;
    /**
     * 模型是否收藏
     * @param collect
     * @return R
     */
    @SysLog("模型收藏")
    @GetMapping("/isCollect")
    public R isCollect(Collect collect){
        collect.setUserId(SecurityUtils.getUser().getId());
        return new R<>(collectService.getCollect(collect));
    }

    /**
     * 个人收藏查询
     * @param page    参数集
     * @param collect 查询参数列表
     * @return  R 数据表集合
     */
    @GetMapping("/page")
    @SysLog("模型收藏列表查询")
    public R findPage (Page page, Collect collect){
        collect.setUserId(SecurityUtils.getUser().getId());
        return new R<>(collectService.findPage(page,collect));
    }

    /**
     * 所有模型查询
     * @param page    参数集
     * @param collect 查询参数列表
     * @return  R 数据表集合
     */
    @GetMapping("/modelAll")
    @SysLog("模型列表查询")
    public R modelAll (Page page, Collect collect){

        return new R<>(collectService.modelAll(page,collect));
    }

    /**
     * 模型是否收藏
     * @param modelId
     * @return R
     */
    @SysLog("模型收藏")
    @GetMapping("/deleteCollect/{modelId}")
    public R deleteCollect(@PathVariable String modelId){
        return new R<>(collectService.remove(modelId,SecurityUtils.getUser().getId()));
    }

    /**
     * 模型评分
     * @param collect
     * @return R
     */
    @SysLog("模型评分")
    @GetMapping("/gradeModel")
    public R gradeModel(Collect collect){
        collect.setUserId(SecurityUtils.getUser().getId());
        return new R<>(collectService.gradeModel(collect));
    }
}
