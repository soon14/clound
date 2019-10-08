package com.cloud.cas.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wengshij
 * @date Created in 2019/8/28 17:31
 * @description:
 * @modified By:wengshij
 */
public interface CasService {
    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    String getUserInfo(HttpServletRequest request);

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    String loginOut(HttpServletRequest request);
}
