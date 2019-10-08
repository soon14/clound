package com.cloud.portal.judged.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.judged.model.model.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lvlinc
 * @date Created in 2019/7/1
 * @description:模型收藏
 * @modified By:lvlinc
 */
@Mapper
public interface CollecMapper extends BaseMapper<Collect> {
    /**
     * 个人收藏查询
     * @param page    参数集
     * @param collect 查询参数列表
     * @return IPage<Collec> 数据表集合
     */
    IPage<Collect> findPage(IPage<Collect> page, @Param("query")Collect collect);
    /**
     * 所有模型查询
     * @param page    参数集
     * @param collect 查询参数列表
     * @return IPage<Collec> 数据表集合
     */
    IPage<Collect> modelAll(IPage<Collect> page, @Param("query")Collect collect);
    /**
     * 模型收藏
     * @param collect
     * @return
     */
    boolean saveCollect(Collect collect);

    /**
     * 模型评分
     * @param collect
     * @return boolean
     */
    boolean gradeModel(Collect collect);

    /**
     * 判断模型是否收藏
     * @param collect
     * @return boolean
     */
    int getCollect (Collect collect);
    /**
     * 取消模型收藏
     * @param modelId
     * @param userId
     * @return boolean
     */
    boolean remove(@Param("modelId")String modelId, @Param("userId")String userId);
}
