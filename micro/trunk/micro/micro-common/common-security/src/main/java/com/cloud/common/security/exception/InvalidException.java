

package com.cloud.common.security.exception;

import com.cloud.common.security.component.MicroAuth2ExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author ygnet
 * @date 2018/7/8
 */
@JsonSerialize(using = MicroAuth2ExceptionSerializer.class)
public class InvalidException extends MicroAuth2Exception {

	public InvalidException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "invalid_exception";
	}

	@Override
	public int getHttpErrorCode() {
		return 426;
	}

}
