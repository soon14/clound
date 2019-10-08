package com.cloud.portal.archive.common.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.archive.common.base.model.DataModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wengshij
 * @date Created in 2019/5/5 9:59
 * @description:
 * @modified By:
 */
public interface DataMapper<T extends DataModel> extends BaseMapper<T> {

    /**
     * 统计特行场所对应的数据表数量
     * @param entity
     * @return
     */
    int getCount(T entity);

    /**
     * 分页获取场所
     * @param page
     * @param entity
     * @return
     */
    IPage<List<T>> findListPage(IPage<T> page,@Param("query") T entity);

    /**
     * 获取特行场所详细信息
     * @param entity
     * @return
     */
    T findOne(T entity);

}
