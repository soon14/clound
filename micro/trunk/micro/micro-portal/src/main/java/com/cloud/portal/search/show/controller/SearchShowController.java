package com.cloud.portal.search.show.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.search.scheme.model.SearchScheme;
import com.cloud.portal.search.scheme.service.SearchSchemeService;
import com.cloud.portal.search.show.model.SearchShow;
import com.cloud.portal.search.show.service.SearchShowService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author wengshij
 * @date Created in 2019/8/5 14:22
 * @description:综合查询前端展示控制台信息
 * @modified By:wengshij
 */
@RestController
@AllArgsConstructor
@RequestMapping("/search/show/")
@Api(value = "show")
public class SearchShowController {

    private final SearchShowService searchShowService;
    private final SearchSchemeService searchSchemeService;


    @GetMapping("index")
    @SysLog("综合查询首页")
    public R index(SearchScheme searchScheme) {
        String id = StringUtils.isBlank(searchScheme.getId()) ? "1" : searchScheme.getId();
        searchScheme = searchSchemeService.getById(id);
        System.out.println(searchScheme);
        return R.ok(searchScheme);
    }

    @GetMapping("listPage")
    @SysLog("综合查询")
    public R listPage(Page page, SearchShow searchShow) {
        return R.ok(searchShowService.listPage(page, searchShow));
    }
}
