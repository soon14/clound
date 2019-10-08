package com.cloud.portal.archive.csgz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.common.security.service.MicroUser;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.archive.csgz.model.Csgz;
import com.cloud.portal.archive.csgz.service.CsgzService;
import com.cloud.portal.archive.dict.model.Dict;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author lvlinc
 * @date Created in 2019/5/22 10:22:00
 * @description: 特行场所关注信息
 * @modified By:lvlinc
 */
@RestController
@AllArgsConstructor
@RequestMapping("/Csgz")
public class CsgzController {
    private final CsgzService csgzService;
    /**
     * 场所是否关注
     * @param csgz
     * @return R
     */
    @SysLog("是否关注")
    @GetMapping("/isgz")
    public R isgz( Csgz csgz){
        MicroUser user = SecurityUtils.getUser();
        csgz.setUserid(user.getId());
        csgz.setCsdwbh(csgz.getDwbh());
        return new R<>(csgzService.getis(csgz));
    }

    /**
     * 分页查询数据表字段
     * @param page    参数集
     * @param csgz 查询参数列表
     * @return  R 数据表集合
     */
    @GetMapping("/page")
    @SysLog("字典列表查询")
    public R findPage (Page page, Csgz csgz){
        MicroUser user = SecurityUtils.getUser();
        csgz.setUserid(user.getId());
        return new R<>(csgzService.findPage(page,csgz));
    }

    /**
     * 场所是否关注
     * @param csdwbh
     * @return R
     */
    @SysLog("是否关注")
    @GetMapping("/nogz/{csdwbh}")
    public R nogz(@PathVariable String csdwbh){
        MicroUser user = SecurityUtils.getUser();
        return new R<>(csgzService.remove(csdwbh,user.getId()));
    }
}
