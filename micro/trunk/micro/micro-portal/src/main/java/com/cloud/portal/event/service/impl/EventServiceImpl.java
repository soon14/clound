package com.cloud.portal.event.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.common.data.datascope.DataScope;
import com.cloud.common.data.util.DataScopeUtil;
import com.cloud.portal.event.mapper.EventMapper;
import com.cloud.portal.event.model.Event;
import com.cloud.portal.event.model.EventSigns;
import com.cloud.portal.event.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * @author ryt
 * @date Created in 2019/8/28 15:59
 * @description: 警综 事件/事件苗头信息
 * @modified By:
 */
@Service
@AllArgsConstructor
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements EventService {


    /**
     * 分页获取事件信息
     * @param page 分页信息
     * @param event 查询参数
     * @return
     */
    @Override
    public IPage<Event> findEventPage(IPage<Event> page, Event event){
        event.setDataScope(DataScopeUtil.getDataScopeSql(new DataScope("a.areaCode","")));
        return this.baseMapper.findEventPage(page,event);
    }

    /**
     * 分页获取事件苗头信息
     * @param page 分页信息
     * @param eventSigns 查询参数
     * @return
     */
    @Override
    public IPage<EventSigns> findEventSignsPage(IPage<EventSigns> page, EventSigns eventSigns){
        eventSigns.setDataScope(DataScopeUtil.getDataScopeSql(new DataScope("a.areaCode","")));
        return this.baseMapper.findEventSignsPage(page,eventSigns);
    }

    /**
     * 根据id查询事件详情
     * @param id
     * @return
     */
    @Override
    public Event getEventById(String id){
        return baseMapper.getEventById(id);
    }

    /**
     * 根据id查询事件苗头详情
     * @param id
     * @return
     */
    @Override
    public EventSigns getEventSignById( String id){
        return baseMapper.getEventSignById(id);
    }

}
