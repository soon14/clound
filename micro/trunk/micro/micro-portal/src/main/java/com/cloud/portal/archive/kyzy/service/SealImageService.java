package com.cloud.portal.archive.kyzy.service;

import com.cloud.portal.archive.common.base.service.DataService;
import com.cloud.portal.archive.kyzy.model.SealImage;


/**
 * @author hyx
 * @date Created in 2019/6/18 9:34
 * @description: 刻印章业-印章图片service层
 * @modified By:
 */
public interface SealImageService extends DataService<SealImage> {

    /**
     * 根据印章id获取章模扫描件
     * @param sealId
     * @return
     */
    public SealImage findSealScanImageBySealId(String sealId);

    /**
     * 根据印章id获取备案印章图像
     * @param sealId
     * @return
     */
    public SealImage findSealAuditImageBySealId(String sealId);

    /**
     * 根据印章id获取排版印章图像
     * @param sealId
     * @return
     */
    public SealImage findSealMakeImageBySealId(String sealId);

}
