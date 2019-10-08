package com.cloud.portal.archive.unitmenu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.common.core.util.R;
import com.cloud.portal.archive.unitmenu.entity.UnitMenu;

import java.util.List;

/**
 * @author ryt
 * @date Created in 2019/5/7 11:18
 * @description: 场所分类显示信息service
 * @modified By:
 */
public interface UnitMenuService extends IService<UnitMenu> {


    /**
     * 获取所有场所菜单信息
     * @param cacheKey 缓存key值
     * @return
     */
    List<UnitMenu> findAll(String cacheKey);

    /**
     * 保存菜单
     * @param unitMenu
     * @return
     */
    R saveMenu(UnitMenu unitMenu);

    /**
     * 更新场所菜单信息
     *
     * @param unitMenu 场所菜单信息
     * @return 成功、失败
     */
    R updateMenuById(UnitMenu unitMenu);

    /**
     * 级联删除菜单
     *
     * @param id 场所菜单ID
     * @return 成功、失败
     */
    R removeMenuById(String id);

}
