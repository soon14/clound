package com.cloud.portal.statistics.rectification.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.statistics.rectification.model.DataRectification;

import java.util.List;

/**
 * @author Molly
 * @date Created in 2019/8/20
 * @description: 统计查询-统计报表-隐患数据整改service接口
 * @modified By: Molly
 * */
public interface DataRectificationService extends IService<DataRectification> {

    /**
     * 获取统计列表
     * @param dataRectification
     * @return
     */
    public List<DataRectification> treeList(DataRectification dataRectification);

}
