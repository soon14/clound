package com.cloud.portal.archive.kyzy.controller;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.archive.common.util.DataUtil;
import com.cloud.portal.archive.kyzy.model.SealImage;
import com.cloud.portal.archive.kyzy.model.SealInfo;
import com.cloud.portal.archive.kyzy.service.ProduceUnitService;
import com.cloud.portal.archive.kyzy.service.SealImageService;
import com.cloud.portal.archive.kyzy.service.SealInfoService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author hyx
 * @date Created in 2019/6/18 9:34
 * @description: 刻印章业-印章控制层
 * @modified By:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/kyzy/seal_info")
public class SealInfoController {

    private final SealInfoService sealInfoService;
    private final SealImageService sealImageService;

    @RequestMapping("/{id}")
    public R findById(@PathVariable String id){
        return new R<>(sealInfoService.findById(id));
    }

    @GetMapping("/count")
    @SysLog("统计印章数量")
    @ApiOperation(httpMethod = "GET", value = "统计印章数量")
    public R getCount(SealInfo info) {
        return new R<>(sealInfoService.getCount(info));
    }


    @GetMapping("/page")
    @SysLog("印章列表(分页)")
    @ApiOperation(httpMethod = "GET", value = "印章列表(分页)")
    public R findList(Page page, SealInfo info) {
        return new R<>(sealInfoService.findListPage(page,info));
    }

    /**
     * 根据印章id获取章模扫描件
     * @param id
     * @param response
     */
    @GetMapping("/scan_image/{id}")
    public void getScanImage( @PathVariable String id, HttpServletResponse response) {
        try {
            InputStream in = null;
            SealImage image = sealImageService.findSealScanImageBySealId(id);
            if (null != image&&image.getSealScanImage()!=null ) {
                in = new ByteArrayInputStream(image.getSealScanImage());
            }
            response.setContentType("application/octet-stream; charset=UTF-8");
            IoUtil.copy(in, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据印章id获取备案印章图像
     * @param id
     * @param response
     */
    @GetMapping("/audit_image/{id}")
    public void getAuditImage( @PathVariable String id, HttpServletResponse response) {
        try {
            InputStream in = null;
            SealImage image = sealImageService.findSealAuditImageBySealId(id);
            if (null != image&&image.getSealAuditImage()!=null ) {
                in = new ByteArrayInputStream(image.getSealAuditImage());
            }
            response.setContentType("application/octet-stream; charset=UTF-8");
            IoUtil.copy(in, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据印章id获取排版印章图像
     * @param id
     * @param response
     */
    @GetMapping("/make_image/{id}")
    public void getMakeImage( @PathVariable String id, HttpServletResponse response) {
        try {
            InputStream in = null;
            SealImage image = sealImageService.findSealMakeImageBySealId(id);
            if (null != image&&image.getSealMakeImage()!=null ) {
                in = new ByteArrayInputStream(image.getSealMakeImage());
            }
            response.setContentType("application/octet-stream; charset=UTF-8");
            IoUtil.copy(in, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
