package com.cloud.admin.api.feign;

import com.cloud.admin.api.entity.SysUser;
import com.cloud.common.core.constant.SecurityConstants;
import com.cloud.common.core.constant.ServiceNameConstants;
import com.cloud.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author wengshij
 * @date Created in 2019/8/21 11:56
 * @description:政务微信微服务远程调用接口信息
 * @modified By:wengshij
 */
@FeignClient(value = ServiceNameConstants.MICRO_PORTAL)
public interface RemoteWeChatService {
    /**
     * 根据acode获取政务微信当前登录用户详细信息
     *
     * @param aCode code值
     * @param from  内部调用
     * @return
     */
    @GetMapping("/we/chat/api/getUser/{aCode}")
    R<SysUser> getUser(@PathVariable("aCode") String aCode
            , @RequestHeader(SecurityConstants.FROM) String from);
}
