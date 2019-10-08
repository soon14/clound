package com.cloud.portal.archive.common.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.archive.common.base.model.DataModel;

import java.util.List;

/**
 * @author wengshij
 * @date Created in 2019/5/5 9:51
 * @description: 智能档案-统一service层
 * @modified By:wengshij
 */
public interface DataService<T extends DataModel> extends IService<T> {

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
    IPage<List<T>> findListPage(IPage<T> page, T entity);

    /**
     * 获取特行场所详细信息
     * @param entity
     * @return
     */
    T findOne(T entity);





}
