package com.cloud.portal.archive.ylcs.controller;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.portal.archive.ylcs.entity.Csba;
import com.cloud.portal.archive.ylcs.entity.Csbafj;
import com.cloud.portal.archive.ylcs.entity.Ryba;
import com.cloud.portal.archive.ylcs.service.CsbafjService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author ryt
 * @date Created in 2019/5/22 17:34
 * @description: 娱乐场所-场所备案附件(证件信息)控制层
 * @modified By:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ylcs/csbafj")
public class CsbafjController {

    private final CsbafjService csbafjService;

    /**
     * 分页查询场所备案附件列表
     * @param page
     * @param csbafj
     * @return
     */
    @GetMapping("/findCsbafjPage")
    public R<IPage> getCsbafjPage(Page page, Csbafj csbafj){
        return new R<>(csbafjService.findCsbafjPage(page,csbafj));
    }

    /**
     * 获取娱乐场所 照片信息
     * @param csbafj 照片ID
     * @return
     */
    @GetMapping("/img")
    public void getRyImg(Csbafj csbafj, HttpServletResponse response) {
        try {
            InputStream in = null;
            csbafj = csbafjService.findImgById(csbafj);
            if (null != csbafj && null != csbafj.getFjNeirong()) {
                in = new ByteArrayInputStream(csbafj.getFjNeirong());
            }
            response.setContentType("application/octet-stream; charset=UTF-8");
            IoUtil.copy(in, response.getOutputStream());
        } catch (Exception e) {
        }
    }

    /**
     * 获取娱乐场所平面图
     * @param csbaId 照片ID
     * @return
     */
    @GetMapping("/pmtImg/{csbaId}")
    public void getPmtImg(@PathVariable String csbaId, HttpServletResponse response) {
        try {
            InputStream in = null;
            Csbafj fj = csbafjService.findPmtById(csbaId);
            if (null != fj && null != fj.getFjNeirong()) {
                in = new ByteArrayInputStream(fj.getFjNeirong());
            }
            response.setContentType("application/octet-stream; charset=UTF-8");
            IoUtil.copy(in, response.getOutputStream());
        } catch (Exception e) {
        }
    }
}
