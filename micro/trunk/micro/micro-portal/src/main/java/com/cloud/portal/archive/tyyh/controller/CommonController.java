package com.cloud.portal.archive.tyyh.controller;

import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.archive.tyyh.model.HwToken;
import com.cloud.portal.archive.tyyh.model.TokenInfo;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/tyyh/common")
public class CommonController {

    @GetMapping("/token")
    @SysLog("获取token")
    @ApiOperation(httpMethod = "GET", value = "获取token")
    public R getToken(String key,String secret){
        TokenInfo info = null;
        try{
            info = HwToken.getToken(key,secret);
            if(info!=null && StringUtils.isNotBlank(info.getAccessToken())){
                return new R(info.getAccessToken());
            }
        }catch (HttpException e){
            e.printStackTrace();
        }
        return null;
    }
}
