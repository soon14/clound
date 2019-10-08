package com.cloud.portal.archive.tyyh.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.archive.tyyh.model.Tyyh;
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
     * @param page   分页参数集
     * @param tyyh 查询参数列表
     * @return R 数据表集合
     */
    @GetMapping("/page")
    @SysLog("异常警告信息")
    public R findPage(Tyyh tyyh, Page page){

        return new R<>(tyyhService.findPage(page,tyyh));
    }

    @PutMapping("/DownUser/{orgid}")
    @SysLog("异常警告信息")
    public R DownUser(@PathVariable String orgid){

        return new R<>(tyyhService.dowmUser(orgid));
    }
}
