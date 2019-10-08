package com.cloud.portal.archive.tyyh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.admin.api.entity.Tyyh;

import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019/6/6
 * @description:
 * @odified By:lvlinc
 */
public interface TyyhService extends IService<Tyyh> {

    /**
     * 分页查询数据表字段
     * @param tyyh 查询参数列表
     * @return 数据表集合
     */
    List<Tyyh> findList(Tyyh tyyh);

    /**
     * 同步统一用户
     * @param tyyh    参数集
     * @return boolean
     */
    boolean dowmUser (Tyyh tyyh);

    /**
     * 同步统一用户
     * @param tyyh    参数集
     * @return boolean
     */
    boolean updateUser (Tyyh tyyh);



    /**
     * 更新一个或多个用户
     * @param tyyh  参数集
     * @return boolean
     */
    boolean dowmUserOne (Tyyh tyyh);
}
