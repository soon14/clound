package com.cloud.portal.archive.kyzy.service.impl;

import com.cloud.portal.archive.common.base.service.impl.DataServiceImpl;
import com.cloud.portal.archive.kyzy.mapper.SealImageMapper;
import com.cloud.portal.archive.kyzy.model.SealImage;
import com.cloud.portal.archive.kyzy.service.SealImageService;
import org.springframework.stereotype.Service;

/**
 * @author hyx
 * @date Created in 2019/6/18 10:56
 * @description: 刻印章业-印章图片service层实现
 * @modified By:
 */
@Service
public class SealImageServiceImpl extends DataServiceImpl<SealImageMapper, SealImage> implements SealImageService {

    /**
     * 根据印章id获取章模扫描件
     * @param sealId
     * @return
     */
    @Override
    public SealImage findSealScanImageBySealId(String sealId) {
        return this.baseMapper.findSealScanImageBySealId(sealId);
    }

    /**
     * 根据印章id获取备案印章图像
     * @param sealId
     * @return
     */
    @Override
    public SealImage findSealAuditImageBySealId(String sealId) {
        return this.baseMapper.findSealAuditImageBySealId(sealId);
    }

    /**
     * 根据印章id获取排版印章图像
     * @param sealId
     * @return
     */
    @Override
    public SealImage findSealMakeImageBySealId(String sealId) {
        return this.baseMapper.findSealMakeImageBySealId(sealId);
    }
}
