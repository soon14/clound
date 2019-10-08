package com.cloud.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.admin.api.entity.SysDictType;

/**
 * @author wuxx
 * @date 2019/2/19 16:51
 * @description: 字典类型service接口
 * @modified By:
 **/
public interface SysDictTypeService extends IService<SysDictType> {

    /**
     * 根据id和type删除字典类型和字典列表
     * @param id 字典类型id
     * @param innerFlag 字典内部标识符
     * @return
     */
    public boolean removeDictType(String id, String innerFlag);
}
