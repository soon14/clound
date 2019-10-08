package com.cloud.portal.archive.ylcs.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.portal.archive.ylcs.entity.Baxj;
import com.cloud.portal.archive.ylcs.service.BaxjService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ryt
 * @date Created in 2019/5/20 10:00
 * @description: 娱乐场所-保安巡检控制层
 * @modified By:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ylcs/baxj")
public class BaxjController {

    private final BaxjService baxjService;

    /**
     * 分页查询人员考勤信息列表
     * @param page
     * @param baxj
     * @return
     */
    @GetMapping("/findBaxjPage")
    public R<IPage> getBaxjPage(Page page, Baxj baxj){
       return new R<>(baxjService.findBaxjPage(page,baxj));
    }

}
