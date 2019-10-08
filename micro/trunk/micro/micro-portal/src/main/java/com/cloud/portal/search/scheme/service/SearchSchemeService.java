package com.cloud.portal.search.scheme.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.common.core.util.R;
import com.cloud.portal.point.index.model.IndexTree;
import com.cloud.portal.search.scheme.model.SearchScheme;
import com.cloud.portal.search.scheme.model.SearchSchemeTree;

import java.util.List;

/**
 * @author ryt
 * @date Created in 2019/8/5 11:21
 * @description: 综合查询-查询方案配置service
 * @modified By:
 */
public interface SearchSchemeService extends IService<SearchScheme> {

    /**
     * 新增查询方案配置
     *
     * @param searchScheme
     * @return
     */
    R saveScheme(SearchScheme searchScheme);

    /**
     * 查询方案配置修改
     *
     * @param searchScheme
     * @return
     */
    R updateScheme(SearchScheme searchScheme);

    /**
     * 查询方案配置删除
     *
     * @param id 方案id
     * @return
     */
    R removeById(String id);

    /**
     * 根据主键ID 更新配置方案的状态、（启用或者停用）
     *
     * @param  id 方案id
     * @param  status 方案状态
     * @return
     */
    R updateStatus(String id, String status);

    /**
     * 获取查询配置方案树结构
     * @param
     * @return
     */
    List<SearchSchemeTree> getSchemeTree();

}
