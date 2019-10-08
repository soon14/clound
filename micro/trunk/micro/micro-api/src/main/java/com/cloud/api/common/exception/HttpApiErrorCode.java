package com.cloud.api.common.exception;

/**
 * @author wengshij
 * @date Created in 2019/8/10 15:53
 * @description:统一异常标识符号
 * @modified By:wengshij
 */
public enum HttpApiErrorCode implements ErrorCode {

    ILLEGAL_VALUE("ILLEGAL_PARAMETER_VALUE", "参数不合法、或缺失"),
    LOST_VALUE("LOST_PARAMETER_VALUE", "参数丢失、配置文件无法找到该接口信息"),
    TOKEN_EXCEPTION("TOKEN_LOSE", "令牌异常"),
    HTTP_CLIENT_EXCEPTION("HTTP_CLIENT_EXCEPTION", "客户端连接异常"),
    API_EXCEPTION("API_EXCEPTION", "接口异常"),
    UN_KNOW_EXCEPTION("UN_KNOW_EXCEPTION", "未知异常");

    private final String code;
    private final String msg;

    HttpApiErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
