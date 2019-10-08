package com.cloud.portal.judged.model.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.judged.model.model.Collect;
import org.apache.ibatis.annotations.Param;

/**
 * @author lvlinc
 * @date Created in 2019/7/2
 * @description:模型收藏
 * @modified By:lvlinc
 */
public interface CollectService extends IService<Collect> {
    /**
     * 个人收藏查询
     * @param page    参数集
     * @param collect 查询参数列表
     * @return IPage<Collec> 数据表集合
     */
    IPage<Collect> findPage(IPage<Collect> page, @Param("query") Collect collect);
    /**
     * 所有模型查询
     * @param page    参数集
     * @param collect 查询参数列表
     * @return IPage<Collec> 数据表集合
     */
    IPage<Collect> modelAll(IPage<Collect> page, @Param("query") Collect collect);
    /**
     * 模型收藏
     * @param collect
     * @return
     */
    boolean saveCollect(Collect collect);
    /**
     * 判断模型是否收藏
     * @param collect
     * @return
     */
    boolean getCollect (Collect collect);
    /**
     * 取消模型收藏
     * @param modelId
     * @param userId
     * @return
     */
    boolean remove(String modelId,String userId);

    /**
     * 模型评分
     * @param collect
     * @return boolean
     */
    boolean gradeModel(Collect collect);
}
