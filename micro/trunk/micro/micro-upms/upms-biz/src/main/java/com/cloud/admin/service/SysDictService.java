

package com.cloud.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.admin.api.entity.SysDict;

import java.io.Serializable;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author ygnet
 * @since 2017-11-19
 */
public interface SysDictService extends IService<SysDict> {

    /**
     * 根据id和type删除字典类型和字典列表
     * @param id 字典类型id
     * @return
     */
    boolean removeTreeById(Serializable id);
}

