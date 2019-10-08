package com.cloud.portal.archive.jingz.controller;

/**
 * @author ryt
 * @date Created in 2019/6/15 11:11
 * @description: 警综治安检查（日常检查）控制层
 * @modified By:
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.portal.archive.jingz.model.Zajc;
import com.cloud.portal.archive.jingz.service.ZajcService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/jz/zajc")
public class ZajcController {
    private final ZajcService zajcService;

    /**
     * 获取治安检查信息列表
     * @param page
     * @param zajc
     * @return
     */
    @GetMapping("/findZajcPage")
    public R<IPage> getZajcPage(Page page, Zajc zajc){
        return new R<>(zajcService.findZajcPage(page,zajc));
    }

    /**
     * 通过ID查询检查详细信息
     *
     * @param systemId
     * @return 检查详细信息
     */
    @GetMapping("/{systemId}")
    public R getById(@PathVariable String systemId) {
        return new R<>(zajcService.getZajcById(systemId));
    }

    /**
     * 获取治安检查项内容
     * @param systemId
     * @return
     */
    @GetMapping("/getZajcConfig/{systemId}")
    public R getZajcConfig(@PathVariable String systemId){
        return new R<>(zajcService.getZajcConfig(systemId));
    }

}
