
package com.cloud.quartz.config;

import com.cloud.quartz.event.SysJobEvent;
import com.cloud.quartz.entity.SysJob;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.quartz.Trigger;
import org.springframework.context.ApplicationEventPublisher;

/**
 * @author wengshij
 * @date Created in 2019/6/25 9:51
 * @description:
 * @modified By:wengshij
 */
@Aspect
@Slf4j
@AllArgsConstructor
public class QuartzInvokeFactory {

	private final ApplicationEventPublisher publisher;

	@SneakyThrows
	void init(SysJob sysJob, Trigger trigger) {
		publisher.publishEvent(new SysJobEvent(sysJob, trigger));
	}
}
