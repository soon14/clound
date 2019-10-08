package com.cloud.portal.archive.jingz.controller;

/**
 * @author ryt
 * @date Created in 2019/6/18 17:14
 * @description: 警综消防监督检查信息控制层
 * @modified By:
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.portal.archive.jingz.model.Xfjc;
import com.cloud.portal.archive.jingz.service.XfjcService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/jz/xfjc")
public class XfjcController {

    private final XfjcService xfjcService;

    /**
     * 获取消防检查信息列表
     * @param page
     * @param xfjc
     * @return
     */
    @GetMapping("/findXfjcPage")
    public R<IPage> getXfjcPage(Page page, Xfjc xfjc){
        return new R<>(xfjcService.findXfjcPage(page,xfjc));
    }

    /**
     * 通过ID查询检查详细信息
     *
     * @param systemId
     * @return 检查详细信息
     */
    @GetMapping("/{systemId}")
    public R getById(@PathVariable String systemId) {
        return new R<>(xfjcService.getXfjcById(systemId));
    }


}
