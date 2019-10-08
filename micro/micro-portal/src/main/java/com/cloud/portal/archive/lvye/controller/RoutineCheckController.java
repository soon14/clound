package com.cloud.portal.archive.lvye.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.portal.archive.lvye.model.RoutineCheck;
import com.cloud.portal.archive.lvye.service.RoutineCheckService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wengshij
 * @date Created in 2019/5/23 14:48
 * @description:
 * @modified By:wengshij
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ly/check")
@Api(value = "旅馆系统日常检查信息")
public class RoutineCheckController {

    private final RoutineCheckService routineCheckService;

    /**
     * 日常检查信息分页
     * 日常检查所属单位ID未必填项
     * （根据当前单位ID 获取所有日常检查信息）
     *
     * @param page         分页对象
     * @param routineCheck 旅业 日常检查对象
     * @return
     */
    @GetMapping("/page")
    public R getPage(Page page, RoutineCheck routineCheck) {
        return new R<>(routineCheckService.findPage(page, routineCheck));
    }


}
