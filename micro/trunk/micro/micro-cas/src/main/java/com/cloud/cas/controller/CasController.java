package com.cloud.cas.controller;

import com.cloud.cas.service.CasService;
import com.cloud.common.core.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wengshij
 * @date Created in 2019/8/26 14:58
 * @description:
 * @modified By:wengshij
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sso")
public class CasController {

    private final CasService casService;

    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    @GetMapping("/user")
    public R getUserInfo(HttpServletRequest request) {
        return R.ok(casService.getUserInfo(request));
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        return casService.loginOut(request);
    }


}
