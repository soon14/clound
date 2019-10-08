package com.cloud.portal.search.show.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.portal.search.show.model.SearchShow;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author wengshij
 * @date Created in 2019/8/5 19:07
 * @description:
 * @modified By:wengshij
 */
public interface SearchShowMapper extends BaseMapper<SearchShow> {
    /**
     * 获取综合查询列表信息
     *
     * @param page
     * @param searchShow
     * @return
     */
    IPage<Map> listPage(Page page,  @Param("query") SearchShow searchShow);
}
