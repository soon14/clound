package com.cloud.portal.event.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.event.model.Event;
import com.cloud.portal.event.model.EventSigns;
import org.apache.ibatis.annotations.Param;


/**
 * @author ryt
 * @date Created in 2019/8/28 15:59
 * @description: 警综 事件/事件苗头信息
 * @modified By:
 */
public interface EventMapper extends BaseMapper<Event> {


    /**
     * 分页获取事件信息
     * @param page 分页信息
     * @param event 查询参数
     * @return
     */
    IPage<Event> findEventPage(IPage<Event> page, @Param("query") Event event);

    /**
     * 分页获取事件信息
     * @param page 分页信息
     * @param eventSigns 查询参数
     * @return
     */
    IPage<EventSigns> findEventSignsPage(IPage<EventSigns> page,@Param("query") EventSigns eventSigns);

    /**
     * 根据id查询事件详情
     * @param id
     * @return
     */
    Event getEventById(@Param("id") String id);

    /**
     * 根据id查询事件苗头详情
     * @param id
     * @return
     */
    EventSigns getEventSignById(@Param("id") String id);

}
