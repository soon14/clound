package com.cloud.api.common.constant;

/**
 * @author wengshij
 * @date Created in 2019/8/8 16:21
 * @description:请求参数
 * @modified By:wengshij
 */
public interface ApiConstant {
    /**
     * 请求类型
     */
    String GRANT_TYPE = "grant_type";
    /**
     * 验证凭证ID
     */
    String CLIENT_ID = "client_id";
    /**
     * 验证凭证密码
     */
    String CLIENT_SECRET = "client_secret";
    /**
     * 令牌刷新 grant_type类型
     */
    String REFRESH_TOKEN = "refresh_token";
    /**
     * 验证凭证 grant_type类型
     */
    String CLIENT_CREDENTIALS = "client_credentials";
    /**
     * CONTENT_TYPE 中的一种类型
     */
    String URLENCODED_TYPE = "application/x-www-form-urlencoded";


    /**
     * 令牌缓存 名称
     */
    String TOKEN_CACHE_NAME = "api_token";
    /**
     * 令牌前缀
     */
    String TOKEN_PREFIX = "key_";
}
