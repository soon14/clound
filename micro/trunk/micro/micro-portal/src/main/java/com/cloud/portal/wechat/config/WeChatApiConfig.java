package com.cloud.portal.wechat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author wengshij
 * @date Created in 2019/8/20 17:03
 * @description:政务微信API配置信息实体类
 * @modified By:wengshij
 */
@Component
@RefreshScope
@Data
@ConfigurationProperties(prefix = "wechat-api")
public class WeChatApiConfig {
    /**
     * 政务微信获取当前用户信息 必须传输的信息
     */
    private String aCode;
    /**
     * 应用唯一标识符
     */
    private String appId;
    /**
     * 企业唯一标识符
     */
    private String developerId;
    /**
     * 企业凭证秘钥
     */
    private String developerKey;
    /**
     * 获取TOKEN的URL地址
     */
    private String tokenUrl;
    /**
     * 获取当前登录用户信息的URL地址
     */
    private String userInfoUrl;

}
