package com.cloud.portal.archive.tyyh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.api.entity.Tyyh;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lvlinc
 * @date Created in 2019/6/6
 * @description:
 * @modified By:lvlinc
 */
@Mapper
public interface TyyhMapper extends BaseMapper<Tyyh> {
    /**
     * 分页查询数据表字段
     * @param page    参数集
     * @param tyyh 查询参数列表
     * @return IPage<tyyh> 数据表集合
     */
    IPage<Tyyh> findPage(Page<Tyyh> page, @Param("query")Tyyh tyyh);

    /**
     * 同步插入统一用户
     * @param tyyh    参数集
     * @return boolean
     */
    boolean dowmUser (Tyyh tyyh);

    /**
     * 同步更新统一用户
     * @param tyyh    参数集
     * @return boolean
     */
    boolean updateUser (Tyyh tyyh);

    /**
     * 判断是否更新
     * @param id    参数集
     * @return boolean
     */
    Tyyh getid (String id);

    /**
     * 更新一个或多个用户
     * @param tyyh  参数集
     * @return boolean
     */
    boolean  dowmUserOne (Tyyh tyyh);

}
