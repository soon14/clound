package com.cloud.portal.judged.lib.controller;


import cn.hutool.http.useragent.UserAgentUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.judged.lib.model.PlanLibrary;
import com.cloud.portal.judged.lib.service.PlanLibraryService;
import com.cloud.portal.metadata.datasource.Util.DbUtil;
import com.cloud.portal.metadata.datasource.entity.RdbSource;
import com.cloud.portal.metadata.datasource.service.RdbSourceService;
import com.cloud.portal.metadata.standardfields.entity.Table;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/PlanLibrary")
@Api(value = "area", description = "预案库模块")
public class PlanLibraryController {
    private final PlanLibraryService planLibraryService;


    /**
     * 分页查询数据表字段
     *
     * @param page    参数集
     * @param planLibrary 查询参数列表
     * @return 数据表集合
     */
    @GetMapping("/page")
    @SysLog("预案库列表查询")
    public R getPlanLibraryPage(Page page, PlanLibrary planLibrary) {
//        IPage list = planLibraryService.findPage(page,planLibrary);
        return new R<>(planLibraryService.findPage(page,planLibrary));
    }

    @PutMapping("/put")
    @SysLog("预案库修改")
    public R putPlanLibrary(@RequestBody PlanLibrary planLibrary) {
        return new R<>(planLibraryService.updateById(planLibrary));
    }

    @DeleteMapping("/removeById/{id}")
    public R removeById(@PathVariable String id) {
        return new R<>(planLibraryService.removeById(id));
    }

    @PostMapping("/add")
    public R save(@RequestBody PlanLibrary planLibrary){
        return new R<>(planLibraryService.insertPlanLibrary(planLibrary));
    }

    /**
     * 返回树形集合
     *
     * @return 异步加载树形列表
     */
    @GetMapping(value = "/fetchLibrarylist")
    public R asyncTreeList(PlanLibrary planLibrary) {
        return new R<>(planLibraryService.fetchLibrarylist(planLibrary));
    }

//    @LoadBalanced
//    @Bean
//    RestTemplate restTemplate(){
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
//        return restTemplate;
//    }
}
