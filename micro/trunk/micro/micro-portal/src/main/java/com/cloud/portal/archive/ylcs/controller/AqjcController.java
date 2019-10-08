package com.cloud.portal.archive.ylcs.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.portal.archive.ylcs.entity.Aqjc;
import com.cloud.portal.archive.ylcs.service.AqjcService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ryt
 * @date Created in 2019/5/21 15:08
 * @description: 娱乐场所-公安日常检查控制层
 * @modified By:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ylcs/aqjc")
public class AqjcController {

    private final AqjcService aqjcService;

    /**
     * 获取安全检查信息列表
     * @param page
     * @param aqjc
     * @return
     */
    @GetMapping("/findAqjcPage")
    public R<IPage> getAqjcPage(Page page, Aqjc aqjc){
        return new R<>(aqjcService.findAqjcPage(page,aqjc));
    }
    /**
     * 通过ID查询检查详细信息
     *
     * @param aqjcId
     * @return 检查详细信息
     */
    @GetMapping("/{aqjcId}")
    public R getById(@PathVariable String aqjcId) {
        return new R<>(aqjcService.getAqjcById(aqjcId));
    }

    /**
     * 获取安全检查信息列表
     * @param aqjcId
     * @return
     */
    @GetMapping("/getAqjcContent/{aqjcId}")
    public R getAqjcContent(@PathVariable String aqjcId){
      return new R<>(aqjcService.getAqjcContent(aqjcId));
    }
}
