package com.cloud.portal.archive.jingz.controller;

/**
 * @author wengshij
 * @date Created in 2019/5/22 9:09
 * @description: 警综-治安管辖单位附件信息
 * @modified By:wengshij
 */

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.portal.archive.jingz.model.PhotoEntity;
import com.cloud.portal.archive.jingz.model.PlaceFile;
import com.cloud.portal.archive.jingz.service.PlaceFileService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RestController
@AllArgsConstructor
@RequestMapping("/jz/file")
@Api(value = "jz", description = "警综-治安管辖单位附件信息")
public class PlaceFileController {
    private final PlaceFileService placeFileService;

    /**
     * 附件信息分页
     * 从业人员所属单位ID未必填项
     * （根据当前单位ID 获取所有附件信息）
     *
     * @param page      分页对象
     * @param placeFile 管辖单位附件信息
     * @return
     */
    @GetMapping("/page")
    public R getPage(Page page, PlaceFile placeFile) {
        return new R<>(placeFileService.findPage(page, placeFile));
    }

    /**
     * 下载文件信息
     *
     * @param systemId 文件id
     * @param response
     * @return
     */
    @GetMapping("/{systemId}")
    public void download(@PathVariable String systemId, HttpServletResponse response) {
        PlaceFile placeFile = placeFileService.getFileBySystemId(systemId);
        if (null != placeFile) {
            placeFileService.downLoad(placeFile.getFiles(), response);
        }

    }


    /**
     * 获取图片信息
     * 来源于B_RYZP
     *
     * @param photoEntity 人员照片信息
     * @param response
     */
    @GetMapping("/getImg")
    public void getImg(PhotoEntity photoEntity, HttpServletResponse response) {
        PhotoEntity photo = placeFileService.getOnePhoto(photoEntity);
        if (null != photo) {
            placeFileService.downLoad(photo.getPhoto(), response);
        }
    }

}
