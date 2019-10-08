
package com.cloud.quartz.util;

import com.cloud.quartz.entity.SysJob;
import com.cloud.quartz.exception.TaskException;

/**
 * @author wengshij
 * @date Created in 2019/6/25 9:51
 * @description: 定时任务反射接口类
 * @modified By:wengshij
 */
public interface ITaskInvoke {

	/**
	 * 执行反射方法
	 *
	 * @param sysJob 配置类
	 * @throws TaskException
	 */
	void invokeMethod(SysJob sysJob) throws TaskException;
}
