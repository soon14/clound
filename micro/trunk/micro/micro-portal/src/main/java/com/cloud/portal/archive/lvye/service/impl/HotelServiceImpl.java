package com.cloud.portal.archive.lvye.service.impl;

import com.cloud.portal.archive.common.base.service.impl.DataServiceImpl;
import com.cloud.portal.archive.lvye.mapper.HotelMapper;
import com.cloud.portal.archive.lvye.model.Hotel;
import com.cloud.portal.archive.lvye.service.HotelService;
import org.springframework.stereotype.Service;

/**
 * @author wengshij
 * @date Created in 2019/5/14 9:32
 * @description: 旅馆业service实现层
 * @modified By:wengshij
 */
@Service
public class HotelServiceImpl extends DataServiceImpl<HotelMapper, Hotel> implements HotelService {
}
