package com.cloud.portal.archive.mechanical.controller;


import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.archive.mechanical.model.Cjcl;
import com.cloud.portal.archive.mechanical.model.Cyry;
import com.cloud.portal.archive.mechanical.service.JxCyryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author lvlinc
 * @date Created in 2019/5/9 17:34
 * @description: 机修从业人员
 * @modified By:lvlinc
 */
@RestController
@AllArgsConstructor
@RequestMapping("/Cyry")
public class JxCyryController {
    private final JxCyryService jxCyryService;

    /**
     * 分页查询
     *
     * @param page    参数集
     * @param cyry 查询参数列表
     * @return 数据表集合
     */
    @GetMapping("/page")
    @SysLog("机修从业人员列表查询")
    public R findCjclPage(Page page, Cyry cyry) {
        return new R<>(jxCyryService.findCyryPage(page,cyry));
    }

    /**
     * 修改
     * @param cyry
     * @return R
     */
    @SysLog("机修从业人员人数")
    @GetMapping("/CountAll")
    public R CountAll(Cyry cyry){

        return new R<> (jxCyryService.CountAll(cyry));
    }

    /**
     * 通过人员zjhm查询人员的详细信息
     *
     * @param xxid 人员
     * @return 单位详细信息
     */
    @GetMapping("/{Ryxq}/{xxid}")
    public R getById(@PathVariable String xxid) {
        return new R<>(jxCyryService.getById(xxid));
    }

    /**
     * 通过人员zjhm查询人员的详细信息
     *
     * @param id 人员xxid
     * @return 单位详细信息
     */
    @GetMapping("/img/{id}")
    public void getImg(@PathVariable String id, HttpServletResponse response) {
        try {
            InputStream in = null;
            Cyry cyry = jxCyryService.CyryImgById(id);
            if (null != cyry && null != cyry.getZp()) {
                in = new ByteArrayInputStream(cyry.getZp());
            }
            response.setContentType("application/octet-stream; charset=UTF-8");
            IoUtil.copy(in, response.getOutputStream());
        } catch (Exception e) {
            //无关紧要的获取输出
        }
    }

}
