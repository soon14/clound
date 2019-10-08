package com.cloud.portal.statistics.rectification.controller;

import com.cloud.admin.api.vo.TreeUtil;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.statistics.rectification.model.DataRectification;
import com.cloud.portal.statistics.rectification.service.DataRectificationService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author Molly
 * @date Created in 2019/8/20
 * @description: 统计查询-统计报表-隐患数据整改控制层
 * @modified By: Molly
 */
@RestController
@AllArgsConstructor
@RequestMapping("/statistics/data_rectification")
public class DataRectificationController {

    @Autowired
    private DataRectificationService dataRectificationService;

    /**
     * 获取统计列表
     * @param dataRectification
     * @return
     */
    @GetMapping("/tree_list")
    @SysLog("获取统计列表")
    public R treeList(DataRectification dataRectification){
        if(dataRectification!=null && StringUtils.isNotBlank(dataRectification.getSendTime())){
            String[] times = dataRectification.getSendTime().split(",");
            if(times!=null && times.length>0){
                dataRectification.setStarTime(times[0]);
                dataRectification.setEndTime(times[1]);
            }
        }
        return new R<>(TreeUtil.bulid(dataRectificationService.treeList(dataRectification),"0"));
    }

}
