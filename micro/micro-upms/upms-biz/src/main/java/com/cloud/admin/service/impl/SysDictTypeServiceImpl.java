package com.cloud.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.entity.SysDict;
import com.cloud.admin.api.entity.SysDictType;
import com.cloud.admin.mapper.SysDictMapper;
import com.cloud.admin.mapper.SysDictTypeMapper;
import com.cloud.admin.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuxx
 * @date 2019/2/19 16:52
 * @description: 字典类型service
 * @modified By:
 **/
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService {

    @Autowired
    private SysDictMapper sysDictMapper;

    /**
     * 根据id和type删除字典类型和字典列表
     * @param id 字典类型id
     * @param innerFlag 字典内部标识符
     * @return
     */
    @Override
    public boolean removeDictType(String id, String innerFlag) {
        this.sysDictMapper.delete(new QueryWrapper<SysDict>().eq("type", innerFlag));
        return this.baseMapper.deleteById(id) > 0;
    }
}
