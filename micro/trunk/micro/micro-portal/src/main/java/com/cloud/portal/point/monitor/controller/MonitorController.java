package com.cloud.portal.point.monitor.controller;

import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.point.detail.service.IndexDetailService;
import com.cloud.portal.point.score.service.PointScoreService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 * @author Molly
 * @date Created in 2019/8/1
 * @description: 积分管理-积分监控
 * @modified By: Molly
 */
@RestController
@AllArgsConstructor
@RequestMapping("/point/monitor")
public class MonitorController {

    @Autowired
    private IndexDetailService indexDetailService;
    @Autowired
    private PointScoreService pointScoreService;

    @GetMapping("/total_today")
    @SysLog("按积分类型统计当天新增积分记录总数")
    public R totalTodayDetail(){
        return new R<>(indexDetailService.totalTodayDetail());
    }

    @GetMapping("/newest_automatic_list/{schemeid}")
    @SysLog("获取最新10条自动积分记录")
    public R findNewestAutomaticList(@PathVariable String schemeid){
        return new R<>(indexDetailService.findNewestAutomaticList(schemeid));
    }

}
