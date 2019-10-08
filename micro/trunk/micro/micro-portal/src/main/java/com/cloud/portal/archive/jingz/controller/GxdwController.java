package com.cloud.portal.archive.jingz.controller;

import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.archive.jingz.model.Gxdw;
import com.cloud.portal.archive.jingz.service.GxdwService;
import com.cloud.portal.archive.lvye.model.Hotel;
import com.cloud.portal.archive.lvye.service.HotelService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wengshij
 * @date Created in 2019/5/7 11:17
 * @description: 警综治安管辖单位控制层（包含七行八所信息）
 * @modified By:wengshij
 */
@RestController
@AllArgsConstructor
@RequestMapping("/archive/place")
public class GxdwController {

    private final GxdwService gxdwService;

    @GetMapping("/findOne")
    @SysLog("场所明细")
    @ApiOperation(httpMethod = "GET", value = "根据主键获取场所信息")
    public R findOne(Gxdw  gxdw) {
        return new R(gxdwService.findOne(gxdw));
    }



}
