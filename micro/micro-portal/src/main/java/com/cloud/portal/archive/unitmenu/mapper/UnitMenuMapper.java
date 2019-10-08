package com.cloud.portal.archive.unitmenu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.portal.archive.unitmenu.entity.UnitMenu;
import org.apache.ibatis.annotations.Param;

/**
 * @author ryt
 * @date Created in 2019/5/7 11:23
 * @description: 场所分类显示信息mapper
 * @modified By:
 */
public interface UnitMenuMapper extends BaseMapper<UnitMenu> {

    /**
     * 更新子菜单所有的父ID信息
     * @param oldParentIds
     * @param newParentIds
     * @return
     */
    Boolean updateChild(@Param("oldParentIds") String oldParentIds, @Param("newParentIds")String newParentIds);

    /**
     * 删除当前ID级其子级
     * @param id
     * @param parentIds
     * @return
     */
    Boolean deleteChild(@Param("id")String id,@Param("parentIds")String parentIds);
}
