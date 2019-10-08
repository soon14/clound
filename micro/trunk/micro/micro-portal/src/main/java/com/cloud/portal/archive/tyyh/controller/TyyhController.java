package com.cloud.portal.archive.tyyh.controller;

import com.cloud.admin.api.entity.Tyyh;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.archive.tyyh.service.TyyhService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
/**
 * @author lvlinc
 * @date Created in 2019/6/6
 * @description:
 * @modified By:lvlinc
 */
@RestController
@AllArgsConstructor
@RequestMapping("/Tyyh")
public class TyyhController {
    private final TyyhService tyyhService;
    /**
     * 分页查询数据表字段
     * @param tyyh 查询参数列表
     * @return R 数据表集合
     *
     */
    @GetMapping("/page")
    @SysLog("统一用户查询")
    public R findList(Tyyh tyyh){

        return new R<>(tyyhService.findList(tyyh));
    }

    @PostMapping("/DownUser")
    @SysLog("同步用户信息")
    public R downUser(@RequestBody Tyyh tyyh){

        return new R<>(tyyhService.dowmUser(tyyh));
    }

    @PostMapping("/DownUserOne")
    @SysLog("同步用户信息")
    public R downUserOne(@RequestBody Tyyh tyyh){

        return new R<>(tyyhService.dowmUserOne(tyyh));
    }
}
