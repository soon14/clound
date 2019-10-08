package com.cloud.portal.archive.tyyh.controller;

import com.cloud.admin.api.vo.TreeUtil;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.archive.dict.model.Dict;
import com.cloud.portal.archive.tyyh.model.Tyjg;
import com.cloud.portal.archive.tyyh.service.TyjgService;
import com.cloud.portal.common.constant.PortalConstants;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.cloud.portal.common.constant.PortalConstants.TYJG_CACHE_KEY;

/**
 * @author lvlinc
 * @date Created in 2019/6/10
 * @description:
 * @modified By:lvlinc
 */
@RestController
@AllArgsConstructor
@RequestMapping("/Tyjg")
public class TyjgController {
    private final TyjgService tyjgService;

    /**
     * 获取统一机构树
     * @param tyjg 查询参数列表
     * @return R 数据表集合
     */
    @GetMapping("getJgTree")
    @SysLog("获取统一机构树")
    public R getTyjg(Tyjg tyjg) {
        return new R<>(tyjgService.getTyjg(tyjg,TYJG_CACHE_KEY));
    }
}
