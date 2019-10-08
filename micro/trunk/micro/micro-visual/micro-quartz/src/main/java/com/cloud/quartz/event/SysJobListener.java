
package com.cloud.quartz.event;

import com.cloud.quartz.entity.SysJob;
import com.cloud.quartz.util.TaskInvokeUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Trigger;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;


/**
 * @author wengshij
 * @date Created in 2019/6/25 9:51
 * @description: 异步监听定时任务多线程事件
 * @modified By:wengshij
 */
@Slf4j
@AllArgsConstructor
public class SysJobListener {

	private TaskInvokeUtil taskInvokeUtil;

	@Async
	@Order
	@EventListener(SysJobEvent.class)
	public void comSysJob(SysJobEvent event) {
		SysJob sysJob = event.getSysJob();
		Trigger trigger = event.getTrigger();
		taskInvokeUtil.invokeMethod(sysJob, trigger);
	}
}
