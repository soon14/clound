package com.cloud.portal.archive.tyyh.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.portal.archive.common.base.mapper.DataMapper;
import com.cloud.portal.archive.csycjg.model.CsYcjg;
import com.cloud.portal.archive.tyyh.model.Tyyh;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lvlinc
 * @date Created in 2019/6/6
 * @description:
 * @modified By:lvlinc
 */
@Mapper
public interface TyyhMapper extends DataMapper<Tyyh> {
    /**
     * 分页查询数据表字段
     * @param page    参数集
     * @param tyyh 查询参数列表
     * @return IPage<tyyh> 数据表集合
     */
    IPage<Tyyh> findPage(Page<Tyyh> page, @Param("query")Tyyh tyyh);

    /**
     * 同步统一用户
     * @param tyyh    参数集
     * @return boolean
     */
    boolean dowmUser (Tyyh tyyh);

}
