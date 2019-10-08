package com.cloud.portal.metadata.datasource.exception;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AppException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;

    public AppException(ErrorCode errorCode, String errorMessage) {
        super(errorCode.toString() + " - " + errorMessage);
        this.errorCode = errorCode;
    }

    private AppException(ErrorCode errorCode, String errorMessage, Throwable cause) {
        super(errorCode.toString() + " - " + getMessage(errorMessage) + " - " + getMessage(cause), cause);

        this.errorCode = errorCode;
    }

    public static AppException asAppException(ErrorCode errorCode, String message) {
        return new AppException(errorCode, message);
    }

    public static AppException asAppException(ErrorCode errorCode, String message, Throwable cause) {
        if (cause instanceof AppException) {
            return (AppException) cause;
        }
        return new AppException(errorCode, message, cause);
    }

    public static AppException asAppException(ErrorCode errorCode, Throwable cause) {
        if (cause instanceof AppException) {
            return (AppException) cause;
        }
        return new AppException(errorCode, getMessage(cause), cause);
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
