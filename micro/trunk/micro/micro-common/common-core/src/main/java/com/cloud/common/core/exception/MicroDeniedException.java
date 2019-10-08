

package com.cloud.common.core.exception;

import lombok.NoArgsConstructor;

/**
 * @author ygnet
 * @date 2018年06月22日16:22:03
 * 403 授权拒绝
 */
@NoArgsConstructor
public class MicroDeniedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MicroDeniedException(String message) {
		super(message);
	}

	public MicroDeniedException(Throwable cause) {
		super(cause);
	}

	public MicroDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	public MicroDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
