package com.cloud.portal.search.scheme.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.portal.search.scheme.model.SearchScheme;

/**
 * @author ryt
 * @date Created in 2019/8/5 11:21
 * @description: 综合查询-查询方案配置
 * @modified By:
 */
public interface SearchSchemeMapper extends BaseMapper<SearchScheme> {

    /**
     * 根据主键ID 更新配置方案的状态、（启用或者停用）
     *
     * @param searchScheme
     * @return
     */
    Integer updateStatus(SearchScheme searchScheme);

}
