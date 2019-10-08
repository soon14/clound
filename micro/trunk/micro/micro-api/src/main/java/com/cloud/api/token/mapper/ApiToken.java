package com.cloud.api.token.mapper;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wengshij
 * @date Created in 2019/8/8 15:24
 * @description:网关令牌信息
 * @modified By:wengshij
 */
@Data
public class ApiToken implements Serializable {
    /**
     * 令牌信息
     */
    private String accessToken;
    /**
     * 刷新令牌信息
     */
    private String refreshToken;
    /**
     * 令牌类型
     */
    private String tokenType;
    /**
     * 服务域
     */
    private String scope;
    /**
     * 令牌存活时间(秒)
     */
    private int expiresIn;
    /**
     * 存储时间
     */
    private Long expiresTime;


}
