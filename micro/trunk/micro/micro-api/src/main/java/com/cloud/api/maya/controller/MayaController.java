package com.cloud.api.maya.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.api.common.config.MayaApiConfig;
import com.cloud.api.common.entity.RestParam;
import com.cloud.api.maya.mapper.Maya;
import com.cloud.api.maya.service.MayaService;
import com.cloud.common.core.util.R;
import com.cloud.common.security.annotation.Inner;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author wengshij
 * @date Created in 2019/8/10 11:52
 * @description: 美亚API接口访问层
 * @modified By:wengshij
 */
@RestController
@AllArgsConstructor
@RequestMapping("/maya/api/")
@Api(value = "maya")
public class MayaController {

    private final MayaService mayaService;
    @Autowired
    private MayaApiConfig mayaApiConfig;

    @GetMapping("token")
    public R getToken() {
        Map<String, String> map = mayaApiConfig.getApiUrl();
        String url = mayaApiConfig.getApiUrlValue("hotelUrl");
        System.out.println(url);
        return R.ok(map);
    }

    @GetMapping("page")
    public R findPage(Page page, Maya maya) {
        return R.ok(mayaService.findPage(page, maya));
    }

    @PostMapping("restPage")
    public R<Page> restPage(@RequestBody RestParam restParam) {
        return R.ok(mayaService.restPage(restParam));
    }


}
