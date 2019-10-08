package com.cloud.portal.area.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.portal.area.entity.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ryt
 * @date Created in 2019/5/28 9:21
 * @description:
 * @modified By:
 */
public interface AreaMapper extends BaseMapper<Area> {

    /**
     * 更新子菜单所有的父ID信息
     * @param oldParentIds
     * @param newParentIds
     * @return
     */
    Boolean updateChildById(@Param("oldParentIds") String oldParentIds, @Param("newParentIds")String newParentIds);

    /**
     * 删除当前ID级其子级
     * @param id
     * @param parentIds
     * @return
     */
    Boolean deleteChildById(@Param("id")String id,@Param("parentIds")String parentIds);

    /***
     * 获取区域列表、分级获取 省 （一级）、地市（二级）、区县（3级）
     * @param area
     * @return
     */
    List<Area> findListLevel(Area area);
}
