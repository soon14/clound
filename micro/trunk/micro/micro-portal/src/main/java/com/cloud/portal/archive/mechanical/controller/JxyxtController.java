package com.cloud.portal.archive.mechanical.controller;

import com.cloud.common.core.util.R;
import com.cloud.portal.archive.common.base.controller.DataController;
import com.cloud.portal.archive.mechanical.service.JxyxtService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author lvlinc
 * @date Created in 2019/5/9 17:34
 * @description: 机修业企业信息
 * @modified By:lvlinc
 */
@RestController
@AllArgsConstructor
@RequestMapping("/Jxyxt")
public class JxyxtController extends DataController {

    private JxyxtService jxyxtService;
    /**
     * 通过ID查询单位的详细信息
     * @param zagldwbm 单位ID
     * @return 单位详细信息
     */
    @GetMapping("/findOne/{zagldwbm}")
    public R getById(@PathVariable String zagldwbm) {
        return new R<>(jxyxtService.getById(zagldwbm));
    }

}
