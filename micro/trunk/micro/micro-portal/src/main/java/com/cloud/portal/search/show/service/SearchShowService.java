package com.cloud.portal.search.show.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.search.show.model.SearchShow;

import java.util.Map;

/**
 * @author wengshij
 * @date Created in 2019/8/5 14:36
 * @description:
 * @modified By:wengshij
 */

public interface SearchShowService extends IService<SearchShow> {
    /**
     * 获取综合查询列表信息
     *
     * @param page
     * @param searchShow
     * @return
     */
    IPage<Map> listPage(Page page, SearchShow searchShow);

    /**
     * 初始化综合查询内容
     *
     * @param searchShow
     * @return
     */
    SearchShow initSearchContent(SearchShow searchShow);
}
