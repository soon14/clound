package com.cloud.portal.area.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.common.core.util.R;
import com.cloud.portal.area.entity.Area;

import java.util.List;

/**
 * @author ryt
 * @date Created in 2019/5/27 14:46
 * @description: 区域service层
 * @modified By:
 */
public interface AreaService  extends IService<Area> {

    /**
     * 获取所有行政区划
     * @param cacheKey 缓存key值
     * @return
     */
    List<Area> findAll(String cacheKey);


    /**
     * 返回当前用户所属区域范围集合
     * @param area
     * @return
     */
    List<Area> getAreaList(Area area);

    /**
     * 保存行政区划
     * @param area
     * @return
     */
    R saveArea(Area area);

    /**
     * 更新行政区划
     *
     * @param area 行政区划信息
     * @return 成功、失败
     */
    R updateAreaById(Area area);

    /**
     * 级联删除行政区划
     *
     * @param id 区划ID
     * @return 成功、失败
     */
    R removeAreaById(String id);

    /***
     * 获取区域列表、分级获取 省 （一级）、地市（二级）、区县（3级）
     * @param area
     * @return
     */
    List<Area> findListLevel(Area area);
}
