package com.cloud.portal.statistics.sitecase.model;

import com.cloud.admin.api.dto.TreeNode;
import com.cloud.portal.archive.common.base.model.DataModel;
import lombok.Data;

import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019/8/7
 * @description:统计报表-特行场所基础情况统计
 * @modified By:lvlinc
 */
@Data
public class TeHangSite extends TreeNode {
    /**id*/
    private String id;
    /**父级id*/
    private String parentId;
    /**行政区划*/
    private String cityCode;
    /**场所类型*/
    private String type;
    /**单位数*/
    private String total;
    /**新增数*/
    private String xzs;
    /**关闭数*/
    private String gbs;
    /**违法率*/
    private String wfl;
    /**开始时间*/
    private String startTime;
    /**结束时间*/
    private String endTime;
}
