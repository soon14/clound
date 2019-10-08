package com.cloud.portal.archive.kyzy.controller;

import com.cloud.common.core.util.R;
import com.cloud.portal.archive.kyzy.service.ProduceUnitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyx
 * @date Created in 2019/6/18 9:34
 * @description: 刻印章业-单位控制层
 * @modified By:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/kyzy/produce_unit")
public class ProduceUnitController {

    private final ProduceUnitService produceUnitService;

    @RequestMapping("/{id}")
    public R findById(@PathVariable String id){
        return new R<>(produceUnitService.findById(id));
    }

}
