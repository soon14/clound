package com.cloud.portal.wechat.entity;

import cn.hutool.json.JSONObject;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * @author wengshij
 * @date Created in 2019/8/20 20:18
 * @description:政务微信令牌信息实体类
 * @modified By:wengshij
 */
@Data
public class WeChatToken implements Serializable {
    /**
     * 令牌信息
     */
    private String token;
    /**
     * 过期时间
     */
    private int expiresIn;
    /**
     * 存储时间
     */
    private Long expiresTime;

    public static WeChatToken initToken(JSONObject jsonObject) {
        if (null != jsonObject) {
            String tokenName = "token", expiresInName = "expiresIn";
            String token = jsonObject.getStr(tokenName);
            int expiresIn = jsonObject.getInt(expiresInName);
            if (StringUtils.isNotBlank(token)) {
                WeChatToken weChatToken = new WeChatToken();
                weChatToken.setToken(token);
                weChatToken.setExpiresIn(expiresIn);
                weChatToken.setExpiresTime(System.currentTimeMillis());
                return weChatToken;
            }
        }
        return null;

    }
}
