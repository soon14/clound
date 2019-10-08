

package com.cloud.common.core.http;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.http.HttpStatus;

import java.io.Serializable;

/**
 * @author wengshij
 * @date Created in 2019/8/8 15:28
 * @description:http相应信息
 * @modified By:wengshij
 */
@ToString
@Accessors(chain = true)
@ApiModel(description = "响应信息主体")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @ApiModelProperty(value = "返回标记：成功标记=200，失败标记=")
    private int code;

    @Getter
    @Setter
    @ApiModelProperty(value = "返回信息")
    private String msg;


    @Getter
    @Setter
    @ApiModelProperty(value = "数据")
    private T data;


    public static <T> Result<T> ok(T data) {
        return restResult(data, HttpStatus.SC_OK, null);
    }

    public static <T> Result<T> ok(T data, String msg) {
        return restResult(data, HttpStatus.SC_OK, msg);
    }

    public static <T> Result<T> failed(T data, String msg, int code) {
        return restResult(data, code, msg);
    }

    public static <T> Result<T> failed(String msg, int code) {
        return restResult(null, code, msg);
    }


    private static <T> Result<T> restResult(T data, int code, String msg) {
        Result<T> apiResult = new Result<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public Result() {
        super();
    }


}
