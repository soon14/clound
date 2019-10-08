

package com.cloud.common.security.exception;

import com.cloud.common.security.component.MicroAuth2ExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author ygnet
 * @date 2018/7/8
 * 自定义OAuth2Exception
 */
@JsonSerialize(using = MicroAuth2ExceptionSerializer.class)
public class MicroAuth2Exception extends OAuth2Exception {
	@Getter
	private String errorCode;

	public MicroAuth2Exception(String msg) {
		super(msg);
	}

	public MicroAuth2Exception(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}
}
