

package com.cloud.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.cloud.common.security.component.MicroAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @author ygnet
 * @date 2018/7/8
 */
@JsonSerialize(using = MicroAuth2ExceptionSerializer.class)
public class ServerErrorException extends MicroAuth2Exception {

	public ServerErrorException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "server_error";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

}
