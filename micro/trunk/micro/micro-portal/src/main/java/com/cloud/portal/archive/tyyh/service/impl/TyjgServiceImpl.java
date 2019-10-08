package com.cloud.portal.archive.tyyh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.vo.TreeUtil;
import com.cloud.common.core.util.R;
import com.cloud.common.core.util.SpringContextHolder;
import com.cloud.portal.archive.dict.model.Dict;
import com.cloud.portal.archive.tyyh.mapper.TyjgMapper;
import com.cloud.portal.archive.tyyh.model.Tyjg;
import com.cloud.portal.archive.tyyh.service.TyjgService;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lvlinc
 * @date Created in 2019/6/10
 * @description:
 * @modified By:lvlinc
 */
@Service
public class TyjgServiceImpl extends ServiceImpl<TyjgMapper, Tyjg> implements TyjgService {


    @Override
    public List<Tyjg> getTyjg(Tyjg tyjg, String cacheKey) {
       if (tyjg.getPid()==null || tyjg.getPid()==""){
           tyjg.setPid("7B038CBFE0FIC09CE0530A282E21C09C");
       }
        return getDeptTree(baseMapper.getTyjg(tyjg));
    }

    private List<Tyjg> getDeptTree(List<Tyjg> tyjgs) {
        List<Tyjg> treeList = tyjgs.stream()
                .filter(tyjg -> !tyjg.getId().equals(tyjg.getParentId()))
                .map(tyjg -> {
                    Tyjg node = new Tyjg();
                    node.setId(tyjg.getId());
                    node.setParentId(tyjg.getPid());
                    node.setName(tyjg.getText());
                    return node;
                }).collect(Collectors.toList());
        return treeList;
    }

}
