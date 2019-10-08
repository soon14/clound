package com.cloud.portal.judged.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.portal.judged.model.model.ModelMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ryt
 * @date Created in 2019/6/21 17:20
 * @description:
 * @modified By:
 */
public interface ModelMenuMapper extends BaseMapper<ModelMenu> {

    /**
     * 获取所有符合查询条件研判模型菜单信息
     * @param modelMenu
     * @return
     */
    List<ModelMenu> queryAll(@Param("query")ModelMenu modelMenu);

    /**
     * 更新子菜单所有的父ID信息
     * @param oldParentIds
     * @param newParentIds
     * @param showHide
     * @return
     */
    Boolean updateChild(@Param("showHide") String showHide,@Param("oldParentIds") String oldParentIds, @Param("newParentIds")String newParentIds);

    /**
     * 删除当前ID级其子级
     * @param id
     * @param parentIds
     * @return
     */
    Boolean deleteChild(@Param("id")String id,@Param("parentIds")String parentIds);

    /**
     * 获取当前ID的子级类型为模型的数据
     * @param
     * @param parentIds
     * @return
     */
    List<ModelMenu> getModelId(@Param("parentIds")String parentIds);

    /**
     * 根据id获取图片
     *
     * @param id 模型菜单ID
     * @return
     */
    ModelMenu getIcon(@Param("id")String id);

    /**
     * 分级获取模型
     *
     * @param modelMenu
     * @return
     */
    List<ModelMenu> getModelByLevel(@Param("query")ModelMenu modelMenu);

    /**
     * 统计目录下的主题模型（app）数量
     * 获取app
     * @param modelMenu
     * @return
     */
    List<ModelMenu> getAppList(@Param("query")ModelMenu modelMenu);


}
