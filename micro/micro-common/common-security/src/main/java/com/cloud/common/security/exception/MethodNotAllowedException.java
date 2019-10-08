

package com.cloud.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.cloud.common.security.component.MicroAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @author ygnet
 * @date 2018/7/8
 */
@JsonSerialize(using = MicroAuth2ExceptionSerializer.class)
public class MethodNotAllowedException extends MicroAuth2Exception {

	public MethodNotAllowedException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "method_not_allowed";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.METHOD_NOT_ALLOWED.value();
	}

}
