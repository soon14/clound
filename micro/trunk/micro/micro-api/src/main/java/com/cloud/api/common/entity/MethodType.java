package com.cloud.api.common.entity;

/**
 * @author wengshij
 * @date Created in 2019/8/10 16:12
 * @description:http client 请求方式
 * @modified By:wengshij
 */
public enum MethodType {
    /**
     * POST请求
     */
    POST("POST", "POST"),
    /**
     * DELETE请求
     */
    DELETE("DELETE", "DELETE"),
    /**
     * PUT请求
     */
    PUT("PUT", "PUT"),
    /**
     * GET请求
     */
    GET("GET", "GET");
    /**
     * code值
     */
    private String code;
    /**
     * value值
     */
    private String value;

     MethodType(String code, String value) {
        this.code = code;
        this.value = value;

    }
    public static MethodType getByValue(String value) {
        for (MethodType searchEnum : values()) {
            if (searchEnum.value.equalsIgnoreCase(value)) {
                return searchEnum;
            }
        }
        return MethodType.POST;
    }

    public String code() {
        return this.code;
    }

    public String value() {
        return this.value;
    }

}
