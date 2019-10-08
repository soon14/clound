/**
 * Copyright &copy; 2015-2020 sunshine All rights reserved.
 */
package com.cloud.admin.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloud.admin.api.entity.SysDict;
import com.cloud.admin.mapper.SysDictMapper;
import com.cloud.admin.mapper.SysDictTypeMapper;
import com.cloud.common.core.util.R;
import com.cloud.common.core.util.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.util.CollectionUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 字典工具类
 *
 * @author wuxinx
 * @version 2013-5-29
 */
public class DictUtils {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private static SysDictMapper dictDao = SpringContextHolder.getBean(SysDictMapper.class);

    private static CacheManager cacheManager = SpringContextHolder.getBean("cacheManager");

    /**
     * 翻译字典
     * @param value 字典值
     * @param type 字典类型
     * @param defaultValue 默认值
     * @return 字典标签
     */
    public static String getDictLabel(String value, String type, String defaultValue) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)) {
            for (SysDict dict : getDictList(type)) {
                if (type.equals(dict.getType()) && value.equals(dict.getValue())) {
                    return dict.getLabel();
                }
            }
        }
        return defaultValue;
    }


    /**
     * 根据字典类型获取字典列表
     * @param type 字典类型
     * @return 字典列表
     */
    public static List<SysDict> getDictList(String type) {
        Cache cache = cacheManager.getCache("dict_details");
        Object obj = cache.get(type);
        List<SysDict> list = (List<SysDict>) ((R)((SimpleValueWrapper) obj).get()).getData();
        if(CollectionUtils.isEmpty(list)){
            list = dictDao.selectList(new QueryWrapper<SysDict>().eq("type", type));
            cache.put(type, list);
        }
        return list;
    }

}
