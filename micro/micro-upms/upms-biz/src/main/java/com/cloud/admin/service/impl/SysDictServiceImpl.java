

package com.cloud.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.entity.SysDict;
import com.cloud.admin.mapper.SysDictMapper;
import com.cloud.admin.service.SysDictService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author ygnet
 * @since 2017-11-19
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    /**
     * 根据id 删除树形字典和全部子级节点
     * @param id
     * @return
     */
    @Override
    public boolean removeTreeById(Serializable id){
        SysDict dict = this.getById(id);
        if(dict != null){
            //是树的时候删除子节点
            return this.remove(new QueryWrapper<SysDict>().likeRight("parent_ids", dict.getParentIds() + "," + dict.getId()).or().eq("id", id));
        }
        return false;
    }

}
