package com.cloud.portal.mapQuery.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.mapQuery.mapper.MapQueryMapper;
import com.cloud.portal.mapQuery.model.MapQuery;
import com.cloud.portal.mapQuery.service.MapQueryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019/8/289:52
 * @description:
 * @modified By:lvlinc
 */
@Service
public class MapQueryServiceImpl extends ServiceImpl<MapQueryMapper, MapQuery> implements MapQueryService {
    @Override
    public List<MapQuery> mapList(MapQuery mapQuery) {
       String dh = ",";
        if (mapQuery.getCityCode().length()!=0){
            mapQuery.setCityCode(mapQuery.getCityCode().replaceAll("0*$", ""));
        }
        if (mapQuery.getRowNum()==0){
            mapQuery.setRowNum(20);
        }
        if (StringUtils.isNotBlank(mapQuery.getPlaceType()) && mapQuery.getPlaceType().contains(dh)) {
            List<String> typeList = Arrays.asList(mapQuery.getPlaceType().split(dh));
            if (null != typeList) {
                    mapQuery.setPlaceTypeList(typeList);
                }
            }
        if (StringUtils.isNotBlank(mapQuery.getLevelType()) && mapQuery.getLevelType().contains(dh)) {
            List<String> levelList = Arrays.asList(mapQuery.getLevelType().split(dh));
            if (null != levelList) {
                mapQuery.setLevelTypeList(levelList);
            }
        }
        return baseMapper.mapList(mapQuery);
    }
}
