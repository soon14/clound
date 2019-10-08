package com.cloud.portal.mapQuery.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.mapQuery.model.MapQuery;

import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019/8/289:52
 * @description:
 * @modified By:lvlinc
 */
public interface MapQueryService extends IService<MapQuery> {
    List<MapQuery> mapList (MapQuery mapQuery);
}
