package com.cloud.portal.judged.model.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.judged.model.mapper.CollecMapper;
import com.cloud.portal.judged.model.model.Collect;
import com.cloud.portal.judged.model.service.CollectService;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author lvlinc
 * @date Created in 2019/7/2
 * @description:模型收藏
 * @modified By:lvlinc
 */
@Service
@AllArgsConstructor
public class CollectServiceImpl extends ServiceImpl<CollecMapper, Collect> implements CollectService {
    private final CacheManager cacheManager;
    @Override
    public IPage<Collect> findPage(IPage<Collect> page, Collect collect) {
        return baseMapper.findPage(page,collect);
    }

    @Override
    public IPage<Collect> modelAll(IPage<Collect> page, Collect collect) {
        return baseMapper.modelAll(page,collect);
    }

    @Override
    public boolean saveCollect(Collect collect) {
        return baseMapper.saveCollect(collect);
    }

    @Override
    public boolean getCollect(Collect collect) {
            String isCollect = "1";
            String notCollect = "0";
            if (collect.getCollect()!=null && collect.getCollect()!=""){
                if (collect.getIsCollect()!=null){
                    if (collect.getIsCollect().equals(isCollect)){
                        baseMapper.remove(collect.getModelId(),collect.getUserId());
                    }else if (collect.getIsCollect().equals(notCollect)){
                        baseMapper.saveCollect(collect);
                    }
                }else {
                    baseMapper.saveCollect(collect);
                }
            }
        return Boolean.TRUE;
    }

    @Override
    public boolean remove(String modelId, String userId) {
        return baseMapper.remove(modelId,userId);
    }

    @Override
    public boolean gradeModel(Collect collect) {
        cacheManager.getCache("modelMenu_details").clear();
        return baseMapper.gradeModel(collect);
    }
}
