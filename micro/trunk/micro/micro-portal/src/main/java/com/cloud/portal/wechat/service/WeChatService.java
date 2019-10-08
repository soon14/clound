package com.cloud.portal.wechat.service;

import com.cloud.common.core.util.R;

/**
 * @author wengshij
 * @date Created in 2019/8/20 17:17
 * @description:政务微信接口层
 * @modified By:wengshij
 */
public interface WeChatService {
    /**
     * 根据acode 获取当前用户的身份证信息
     *
     * @param aCode 政务微信跳转时获取到的 有效凭证
     * @return
     */
    R getIdCardByACode(String aCode);
}
