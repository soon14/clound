package com.cloud.portal.mapQuery.controller;

import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.mapQuery.model.MapQuery;
import com.cloud.portal.mapQuery.service.MapQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lvlinc
 * @date Created in 2019/8/289:53
 * @description:特行场所地图查询
 * @modified By:lvlinc
 */
@RestController
@AllArgsConstructor
@RequestMapping("mapQuery")
public class MapQueryController {
    private final MapQueryService mapQueryService;
    @GetMapping("/mapList")
    @SysLog("特行场所地图查询")
    public R findPage(MapQuery mapQuery){
        return new R<>(mapQueryService.mapList(mapQuery));
    }
}
