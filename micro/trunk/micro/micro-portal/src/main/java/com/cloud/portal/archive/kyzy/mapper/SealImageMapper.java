package com.cloud.portal.archive.kyzy.mapper;

import com.cloud.portal.archive.common.base.mapper.DataMapper;
import com.cloud.portal.archive.kyzy.model.SealImage;
import org.apache.ibatis.annotations.Param;

/**
 * @author hyx
 * @date Created in 2019/5/9 19:05
 * @description: 刻印章业-印章图片表mapper层
 * @modified By:
 */
public interface SealImageMapper extends DataMapper<SealImage> {

    /**
     * 根据印章id获取章模扫描件
     * @param sealId
     * @return
     */
    public SealImage findSealScanImageBySealId(@Param("sealId") String sealId);

    /**
     * 根据印章id获取备案印章图像
     * @param sealId
     * @return
     */
    public SealImage findSealAuditImageBySealId(@Param("sealId") String sealId);

    /**
     * 根据印章id获取排版印章图像
     * @param sealId
     * @return
     */
    public SealImage findSealMakeImageBySealId(@Param("sealId") String sealId);
}
