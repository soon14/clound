package com.cloud.portal.event.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.event.model.Event;
import com.cloud.portal.event.model.EventSigns;


/**
 * @author ryt
 * @date Created in 2019/8/28 15:59
 * @description: 警综 事件/事件苗头信息
 * @modified By:
 */
public interface EventService extends IService<Event> {

    /**
     * 分页获取事件信息
     * @param page 分页信息
     * @param event 查询参数
     * @return
     */
    IPage<Event> findEventPage(IPage<Event> page, Event event);

    /**
     * 分页获取事件苗头信息
     * @param page 分页信息
     * @param eventSigns 查询参数
     * @return
     */
    IPage<EventSigns> findEventSignsPage(IPage<EventSigns> page, EventSigns eventSigns);

    /**
     * 根据id查询事件详情
     * @param id
     * @return
     */
    Event getEventById(String id);

    /**
     * 根据id查询事件苗头详情
     * @param id
     * @return
     */
    EventSigns getEventSignById( String id);

}
