
package com.cloud.quartz.event;

import com.cloud.quartz.config.QuartzInvokeFactory;
import com.cloud.quartz.util.TaskInvokeUtil;
import com.cloud.quartz.service.SysJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author wengshij
 * @date Created in 2019/6/25 9:51
 * @description: 多线程自动配置
 * @modified By:wengshij
 */
@EnableAsync
@Configuration
@ConditionalOnWebApplication
public class EventAutoConfiguration {
	@Autowired
	private TaskInvokeUtil taskInvokeUtil;
	@Autowired
	private SysJobLogService sysJobLogService;

	@Bean
	public SysJobListener sysJobListener() {
		return new SysJobListener(taskInvokeUtil);
	}

	@Bean
	public QuartzInvokeFactory quartzInvokeFactory(ApplicationEventPublisher publisher) {
		return new QuartzInvokeFactory(publisher);
	}

	@Bean
	public SysJobLogListener sysJobLogListener() {
		return new SysJobLogListener(sysJobLogService);
	}

	@Bean
	public TaskInvokeUtil taskInvokeUtil(ApplicationEventPublisher publisher) {
		return new TaskInvokeUtil(publisher);
	}

}
