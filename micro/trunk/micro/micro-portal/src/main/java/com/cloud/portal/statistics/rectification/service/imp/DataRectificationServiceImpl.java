package com.cloud.portal.statistics.rectification.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.statistics.rectification.mapper.DataRectificationMapper;
import com.cloud.portal.statistics.rectification.model.DataRectification;
import com.cloud.portal.statistics.rectification.service.DataRectificationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Molly
 * @date Created in 2019/8/20
 * @description: 统计查询-统计报表-隐患数据整改service实现
 * @modified By: Molly
 */
@Service
public class DataRectificationServiceImpl extends ServiceImpl<DataRectificationMapper, DataRectification> implements DataRectificationService {

    @Override
    public List<DataRectification> treeList(DataRectification dataRectification) {
        if(dataRectification!=null&& StringUtils.isNotBlank(dataRectification.getCode())){
            dataRectification.setCode(dataRectification.getCode().replaceAll("0*$", ""));
        }
        return this.baseMapper.treeList(dataRectification);
    }
}
