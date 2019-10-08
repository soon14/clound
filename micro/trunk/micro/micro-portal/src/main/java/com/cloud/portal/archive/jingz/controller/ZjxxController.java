package com.cloud.portal.archive.jingz.controller;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.archive.jingz.model.Zjxx;
import com.cloud.portal.archive.jingz.service.ZjxxService;
import com.cloud.portal.archive.mechanical.model.Cyry;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author lvlinc
 * @date Created in 2019/6/15
 * @description:
 * @modified By:lvlinc
 */
@RestController
@AllArgsConstructor
@RequestMapping("Zjxx")
public class ZjxxController{
    private ZjxxService zjxxService;
    @GetMapping("/page")
    @SysLog("警钟证件信息列表查询")
    public R getPage(Page page, Zjxx zjxx) {
        return new R<>(zjxxService.findPage(page, zjxx));
    }

    /**
     * 通过fileid获取照片信息
     * @param fileid
     * @return 获取照片信息
     */
    @GetMapping("/img/{fileid}")
    public void getImg(@PathVariable String fileid, HttpServletResponse response) {
        try {
            InputStream in = null;
            Zjxx zjxx = zjxxService.imgById(fileid);
            if (null != zjxx && null != zjxx.getFiles()) {
                in = new ByteArrayInputStream(zjxx.getFiles());
            }
            response.setContentType("application/octet-stream; charset=UTF-8");
            IoUtil.copy(in, response.getOutputStream());
        } catch (Exception e) {
            //无关紧要的获取输出
        }
    }
}
