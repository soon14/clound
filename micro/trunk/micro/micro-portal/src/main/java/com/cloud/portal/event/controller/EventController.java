package com.cloud.portal.event.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.event.model.Event;
import com.cloud.portal.event.model.EventSigns;
import com.cloud.portal.event.service.EventService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ryt
 * @date Created in 2019/8/28 15:59
 * @description: 事件/事件苗头信息
 * @modified By:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/event")
@Api(value = "event", description = "警综-事件/事件苗头信息")
public class EventController {

    private final EventService eventService;

    @GetMapping("/eventPage")
    @SysLog("移动警务-特行场所事件查询")
    public R findEventPage(Page page, Event event){
        return new R<>(eventService.findEventPage(page,event));
    }


    @GetMapping("/eventSignsPage")
    @SysLog("移动警务-特行场所事件苗头查询")
    public R findEventSignsPage(Page page, EventSigns eventSigns){
        return new R<>(eventService.findEventSignsPage(page,eventSigns));
    }

    /**
     * 根据id查询事件详情
     * @param id
     * @return
     */
    @GetMapping("/eventDetail/{id}")
    public R getEventById(@PathVariable String id) {
        return R.ok(eventService.getEventById(id));
    }

    /**
     * 根据id查询事件苗头详情
     * @param id
     * @return
     */
    @GetMapping("/eventSignsDetail/{id}")
    public R getEventSignById(@PathVariable String id) {
        return R.ok(eventService.getEventSignById(id));
    }

}
