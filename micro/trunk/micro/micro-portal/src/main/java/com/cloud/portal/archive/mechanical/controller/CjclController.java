package com.cloud.portal.archive.mechanical.controller;


import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.archive.lvye.model.Employee;
import com.cloud.portal.archive.mechanical.model.Cjcl;
import com.cloud.portal.archive.mechanical.service.CjclService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author lvlinc
 * @date Created in 2019/5/9 17:34
 * @description: 机修承接车辆
 * @modified By:lvlinc
 */
@RestController
@AllArgsConstructor
@RequestMapping("/Cjcl")
public class CjclController {
    private final CjclService cjclService;

    /**
     * 分页查询
     *
     * @param page    参数集
     * @param cjcl 查询参数列表
     * @return 数据表集合
     */
    @GetMapping("/page")
    @SysLog("机修承接车辆列表查询")
    public R findCjclPage(Page page, Cjcl cjcl) {
        return new R<>(cjclService.findCjclPage(page,cjcl));
    }

    /**
     * 修改
     * @param cjcl
     * @return R
     */
    @SysLog("承接车辆人数")
    @GetMapping("/CountAll")
    public R CountAll(Cjcl cjcl){

        return new R<> (cjclService.CountAll(cjcl));
    }

    /**
     * 通过人员jdchphm查询人员的详细信息
     *
     * @param ywlsh
     * @return 单位详细信息
     */
    @GetMapping("/{Clxq}/{ywlsh}")
    public R getById(@PathVariable String ywlsh) {
        return new R<>(cjclService.getById(ywlsh));
    }

    @GetMapping("/img/{id}")
    public void getImg(@PathVariable String id, HttpServletResponse response) {
        try {
            InputStream in = null;
            Cjcl cjcl = cjclService.findImgById(id);
            if (null != cjcl && null != cjcl.getClzczp()) {
                in = new ByteArrayInputStream(cjcl.getClzczp());
            }
            response.setContentType("application/octet-stream; charset=UTF-8");
            IoUtil.copy(in, response.getOutputStream());
        } catch (Exception e) {
            //无关紧要的获取输出
        }
    }
}
