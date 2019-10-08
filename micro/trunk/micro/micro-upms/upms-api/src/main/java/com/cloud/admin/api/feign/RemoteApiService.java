package com.cloud.admin.api.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.api.dto.RestParamDTO;
import com.cloud.common.core.constant.ServiceNameConstants;
import com.cloud.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author wengshij
 * @date Created in 2019/8/13
 * @description:微服务远程调用rest接口
 * @modified By:wengshij
 */
@FeignClient(value = ServiceNameConstants.API_SERVICE)
public interface RemoteApiService {


    /**
     * 调用第三方rest接口信息
     * @param restParamDTO
     * @return
     */
    @PostMapping("/maya/api/restPage")
    R<Page> restPage(@RequestBody RestParamDTO restParamDTO);
}
