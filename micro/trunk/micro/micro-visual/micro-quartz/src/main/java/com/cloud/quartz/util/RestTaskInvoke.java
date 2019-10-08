
package com.cloud.quartz.util;

import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.core.util.R;
import com.cloud.quartz.entity.SysJob;
import com.cloud.quartz.exception.TaskException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author wengshij
 * @date Created in 2019/6/25 9:51
 * @description: 定时任务rest反射实现
 * @modified By:wengshij
 */
@Slf4j
@Component("restTaskInvoke")
@AllArgsConstructor
public class RestTaskInvoke implements ITaskInvoke {

	private RestTemplate restTemplate;

	@Override
	public void invokeMethod(SysJob sysJob) throws TaskException {
		R r = restTemplate.getForObject(sysJob.getExecutePath(), R.class);
		if (CommonConstants.FAIL == r.getCode()) {
			log.error("定时任务restTaskInvok异常,执行任务：{}", sysJob.getExecutePath());
			throw new TaskException("定时任务restTaskInvok业务执行失败,任务：" + sysJob.getExecutePath());
		}
	}
}
