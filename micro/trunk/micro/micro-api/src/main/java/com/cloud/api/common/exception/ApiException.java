package com.cloud.api.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author wengshij
 * @date Created in 2019/8/10 15:44
 * @description:自定义异常信息
 * @modified By:wengshij
 */
public class ApiException extends RuntimeException {
    private ErrorCode errorCode;

    public ApiException(ErrorCode errorCode, String errorMessage) {
        super(errorCode.toString() + " - " + errorMessage);
        this.errorCode = errorCode;
    }



    private ApiException(ErrorCode errorCode, String errorMessage, Throwable cause) {
        super(errorCode.toString() + " - " + getMessage(errorMessage) + " - " + getMessage(cause), cause);

        this.errorCode = errorCode;
    }

    public static ApiException asApiException(ErrorCode errorCode, String message) {
        return new ApiException(errorCode, message);
    }

    public static ApiException asApiException(ErrorCode errorCode, String message, Throwable cause) {
        if (cause instanceof ApiException) {
            return (ApiException) cause;
        }
        return new ApiException(errorCode, message, cause);
    }


    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    private static String getMessage(Object obj) {
        if (obj == null) {
            return "";
        }

        if (obj instanceof Throwable) {
            StringWriter str = new StringWriter();
            PrintWriter pw = new PrintWriter(str);
            ((Throwable) obj).printStackTrace(pw);
            return str.toString();
        } else {
            return obj.toString();
        }
    }
}
