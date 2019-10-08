package com.cloud.portal.archive.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.dto.DictTree;
import com.cloud.admin.api.entity.SysDict;
import com.cloud.admin.api.vo.TreeUtil;
import com.cloud.common.core.util.R;
import com.cloud.common.core.util.SpringContextHolder;
import com.cloud.portal.archive.dict.mapper.DictMapper;
import com.cloud.portal.archive.dict.model.Dict;
import com.cloud.portal.archive.dict.service.DictService;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019-5-27
 * @description: 字典
 * @modified By:lvlinc
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public IPage<Dict> findDict(IPage<Dict> page, Dict dict) {
        return baseMapper.findPage(page,dict);
    }

    @Override
    public List<Dict> getDict(String codetype,String lyxt) {
        List<Dict> list1 = new ArrayList<>();
        List<Dict> list = baseMapper.getDict(codetype,lyxt);
        if (list.get(0).getParentId()!=null){
            List<Dict> trees = new ArrayList<>();
            Dict node;
            for (Dict dict1 : list) {
                node = new Dict();
                node.setId(dict1.getIds());
                node.setParentId(dict1.getParentId());
                node.setLabel(dict1.getTitle());
                node.setType(dict1.getCodetype());
                node.setValue(dict1.getCode());
                node.setParentIds(dict1.getParentIds());
                trees.add(node);
            }
            list1 = TreeUtil.bulid(trees, 0);
        }else {
            for (Dict dict1 : list) {
                dict1.setLabel(dict1.getTitle());
                dict1.setType(dict1.getCodetype());
                dict1.setValue(dict1.getCode());
                list1.add(dict1);
            }
        }
        return list1;
    }

    @Override
    public String getDictLabel(String codetype, String code, String lyxt) {
        String Label = "";
        if (StringUtils.isNotBlank(codetype) && StringUtils.isNotBlank(code)&& StringUtils.isNotBlank(lyxt)) {
            for (Dict dict : getDictList(codetype,lyxt)) {
                if (codetype.equals(dict.getCodetype()) && code.equals(dict.getCode())&& lyxt.equals(dict.getLyxt())) {
                    Label = dict.getTitle();
                    break;
                }
            }
        }
        return Label;
    }

    public List<Dict> getDictList(String codetype, String lyxt) {
        CacheManager cacheManager = SpringContextHolder.getBean("cacheManager");
        Cache cache = cacheManager.getCache("dict_details");
        String kye = (codetype).concat("-").concat(lyxt);
        Object obj = cache.get(kye);
        List<Dict> list = null;
                if(obj!=null){
                    list = (List<Dict>) ((R)((SimpleValueWrapper) obj).get()).getData();
                }
        if(CollectionUtils.isEmpty(list)){
            list = baseMapper.getDict(codetype,lyxt);
            cache.put(kye, list);
        }
        return list;
    }

}
