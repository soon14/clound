

package com.cloud.common.security.exception;

import com.cloud.common.security.component.MicroAuth2ExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

/**
 * @author ygnet
 * @date 2018/7/8
 */
@JsonSerialize(using = MicroAuth2ExceptionSerializer.class)
public class UnauthorizedException extends MicroAuth2Exception {

	public UnauthorizedException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "unauthorized";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.UNAUTHORIZED.value();
	}

}
