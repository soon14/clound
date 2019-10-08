package com.cloud.portal.archive.tyyh.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.portal.archive.common.base.service.DataService;
import com.cloud.portal.archive.tyyh.model.Tyyh;
import org.apache.ibatis.annotations.Param;

/**
 * @author lvlinc
 * @date Created in 2019/6/6
 * @description:
 * @modified By:lvlinc
 */
public interface TyyhService extends DataService<Tyyh> {
    /**
         * 分页查询数据表字段
         * @param page    参数集
         * @param tyyh 查询参数列表
         * @return 数据表集合
         */
    IPage<Tyyh> findPage(Page<Tyyh> page, @Param("query")Tyyh tyyh);

    /**
     * 分页查询数据表字段
     * @param orgid    参数集
     * @return boolean
     */
    boolean dowmUser (String orgid);
}
