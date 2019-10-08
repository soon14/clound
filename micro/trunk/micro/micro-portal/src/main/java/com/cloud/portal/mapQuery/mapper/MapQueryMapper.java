package com.cloud.portal.mapQuery.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.portal.mapQuery.model.MapQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019/8/289:53
 * @description:
 * @modified By:lvlinc
 */
public interface MapQueryMapper extends BaseMapper<MapQuery> {
    List<MapQuery> mapList (@Param("query")MapQuery mapQuery);
}
