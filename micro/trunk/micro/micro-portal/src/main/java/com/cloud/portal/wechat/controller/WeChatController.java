package com.cloud.portal.wechat.controller;

import com.cloud.common.core.util.R;
import com.cloud.common.security.annotation.Inner;
import com.cloud.portal.wechat.service.WeChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wengshij
 * @date Created in 2019/8/20 17:17
 * @description:政务微信控制层
 * @modified By:wengshij
 */
@RestController
@AllArgsConstructor
@RequestMapping("/we/chat/api")
public class WeChatController {

    private final WeChatService weChatService;

    /**
     * 获取指定用户全部信息
     *
     * @return 用户信息
     */
    @Inner
    @GetMapping("/getUser/{aCode}")
    public R getUser(@PathVariable String aCode) {
        return weChatService.getIdCardByACode(aCode);
    }
}
