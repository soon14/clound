package com.cloud.portal.archive.kyzy.model;

import com.cloud.portal.archive.common.base.model.DataModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author hyx
 * @date Created in 2019/6/17 17:05
 * @description: 刻印章业-印章图片表
 * @modified By:
 */
@Data
public class SealImage extends DataModel<SealImage> {

    private static final long serialVersionUID = 1L;

    /**用户编码*/
    private String accountId;
    /***/
    private String bizNum;
    /**雕刻状态*/
    private String carvStatus;
    /**市编码*/
    private String cityId;
    /**压缩标记(1,3压缩,其它未压缩)*/
    private String compressionMark;
    /**创建时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;
    /**创建用户*/
    private String createUserId;
    /**比对值*/
    private long dicsernValue;
    /**是否加密*/
    private String encryption;
    /**图像高度*/
    private int height;
    /**省编码*/
    private String provinceId;
    /**备案印章图像*/
    private byte[] sealAuditImage;
    /**印章编码*/
    private String sealId;
    /**排版印章图像*/
    private byte[] sealMakeImage;
    /**章模扫描件*/
    private byte[] sealScanImage;
    /***/
    private String syncFlag;
    /**修改时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTime;
    /**修改用户*/
    private String updateUserId;
    /**图像宽度*/
    private int width;

}
