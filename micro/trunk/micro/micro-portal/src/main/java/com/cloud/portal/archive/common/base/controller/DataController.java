package com.cloud.portal.archive.common.base.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.archive.common.base.model.DataModel;
import com.cloud.portal.archive.common.util.DataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wengshij
 * @date Created in 2019/5/6 9:31
 * @description: 特行场所智能档案公共控制层
 * @modified By:wengshij
 */
@RestController
@RequestMapping("/archive/common")
@Api(value = "archive", description = "智能档案统一入口")
public class DataController {

    @GetMapping("/count")
    @SysLog("统计场所信息")
    @ApiOperation(httpMethod = "GET", value = "统计所有场所信息")
    public R getCount(DataModel model) {
        return new R<>(DataUtil.initCount(model));
    }


    @GetMapping("/page")
    @SysLog("场所查询")
    @ApiOperation(httpMethod = "GET", value = "场所信息查询")
    public R findList(Page page, DataModel model) {
        return new R<>(DataUtil.initPage(page, model));
    }

    @GetMapping("/findOne")
    @SysLog("场所明细")
    @ApiOperation(httpMethod = "GET", value = "根据主键获取场所信息")
    public R findOne(DataModel model) {
        return DataUtil.initEntity(model);
    }

    @GetMapping("/findTab")
    @ApiOperation(httpMethod = "GET", value = "获得所有特行场所分类列表")
    public R findTab() {
        return new R<>(DataUtil.initTabList(Boolean.FALSE));
    }
}
