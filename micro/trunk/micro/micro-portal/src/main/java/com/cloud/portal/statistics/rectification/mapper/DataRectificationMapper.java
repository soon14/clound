package com.cloud.portal.statistics.rectification.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.portal.statistics.rectification.model.DataRectification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Molly
 * @date Created in 2019/8/20
 * @description: 统计查询-统计报表-隐患数据整改mapper
 * @modified By: Molly
 */
public interface DataRectificationMapper extends BaseMapper<DataRectification> {

    public List<DataRectification> treeList(@Param(value = "query") DataRectification dataRectification);

}
