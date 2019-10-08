
package com.cloud.quartz.exception;

/**
 * @author wengshij
 * @date Created in 2019/6/25 9:51
 * @description: 定时任务统一异常信息
 * @modified By:wengshij
 */
public class TaskException extends Exception {

	public TaskException() {
		super();
	}

	public TaskException(String msg) {
		super(msg);
	}
}
