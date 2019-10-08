package com.cloud.portal.archive.lvye.controller;

import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.archive.common.base.model.DataModel;
import com.cloud.portal.archive.common.util.DataUtil;
import com.cloud.portal.archive.lvye.model.Hotel;
import com.cloud.portal.archive.lvye.service.HotelService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wengshij
 * @date Created in 2019/5/14 9:31
 * @description: 旅馆业控制层
 * @modified By:wengshij
 */
@RestController
@RequestMapping("/archive/ly")
@AllArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/findOne")
    @SysLog("场所明细")
    @ApiOperation(httpMethod = "GET", value = "根据主键获取场所信息")
    public R findOne(Hotel hotel) {

        return new R(hotelService.findOne(hotel));
    }


}
