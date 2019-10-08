package com.cloud.portal.judged.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.common.core.util.R;
import com.cloud.portal.judged.model.model.ModelMenu;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @author ryt
 * @date Created in 2019/6/21 17:18
 * @description: 研判模型配置
 * @modified By:
 */
public interface ModelMenuService extends IService<ModelMenu> {

    /**
     * 获取所有研判模型菜单信息
     * @param cacheKey 缓存key值
     * @return
     */
    List<ModelMenu> findAll(String cacheKey);

    /**
     * 获取所有符合查询条件研判模型菜单信息
     * @param modelMenu
     * @return
     */
    List<ModelMenu> queryAll(ModelMenu modelMenu);

    /**
     * 保存模型配置
     * @param modelMenu
     * @return
     */
    R saveModel(ModelMenu modelMenu);

    /**
     * 更新模型配置
     * @param modelMenu
     * @return
     */
    R updateModel(ModelMenu modelMenu);

    /**
     * 级联删除模型配置菜单
     *
     * @param id 模型菜单ID
     * @return
     */
    R removeMenuById(String id);

    /**
     * 文件上传
     * @param multipartRequest 带有文件流的request
     * @return 上传结果
     * @throws Exception
     */
    Map<String, Object> uploadIcon(MultipartHttpServletRequest multipartRequest) throws Exception;

    /**
     * 根据id获取图片
     *
     * @param id 模型菜单ID
     * @return
     */
    ModelMenu getIcon(String id);

    /**
     * 将模型图标放进缓存
     *
     * @param modelMenu
     * @return
     */
    void cacheIcon(ModelMenu modelMenu);

    /**
     * 删除研判模型图标
     *
     * @param
     * @return
     */
    void delIcon();

    /**
     * 分级获取模型
     *@param roleId 角色id，根据角色id 存取数据到缓存
     * @param modelMenu
     * @return
     */
    List<ModelMenu> getModelByLevel(ModelMenu modelMenu,Integer roleId);

    /**
     * 统计目录下的主题模型（app）数量
     *@param roleId 角色id，根据角色id 存取数据到缓存
     * @param id
     * @return
     */
     Integer countModel(Integer roleId,String id);

    /**
     * 获取所有模型（app）
     *@param roleId 角色id，根据角色id 存取数据到缓存
     * @param modelMenu 模型查询条件
     * @return
     */
    List<ModelMenu> getApp(ModelMenu modelMenu,Integer roleId);
}
