package com.cloud.api.common.exception;

/**
 * @author wengshij
 * @date Created in 2019/8/10 15:44
 * @description:错误代码描述
 * @modified By:wengshij
 */
public interface ErrorCode {
    /**
     * 错误编码
     *
     * @return
     */
    String getCode();

    /**
     * 错误描述
     *
     * @return
     */
    String getMsg();

    /**
     * 须提供toString的实现
     *
     * @return
     */
    @Override
    String toString();
}
