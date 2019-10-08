
package com.cloud.quartz.util;


import cn.hutool.core.util.StrUtil;
import com.cloud.quartz.entity.SysJob;
import com.cloud.quartz.exception.TaskException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * @author wengshij
 * @date Created in 2019/6/25 9:51
 * @description: 定时任务jar 接口
 * @modified By:wengshij
 */
@Slf4j
@Component("jarTaskInvoke")
public class JarTaskInvoke implements ITaskInvoke {

	@Override
	public void invokeMethod(SysJob sysJob) throws TaskException {
		ProcessBuilder processBuilder = new ProcessBuilder();
		File jar = new File(sysJob.getExecutePath());
		processBuilder.directory(jar.getParentFile());
		List<String> commands = new ArrayList<>();
		commands.add("java");
		commands.add("-jar");
		commands.add(sysJob.getExecutePath());
		if (StrUtil.isNotEmpty(sysJob.getMethodParamsValue())) {
			commands.add(sysJob.getMethodParamsValue());
		}
		processBuilder.command(commands);
		try {
			Process process = processBuilder.start();
		} catch (IOException e) {
			log.error("定时任务jar反射执行异常,执行任务：{}", sysJob.getExecutePath());
			throw new TaskException("定时任务jar反射执行异常,执行任务：" + sysJob.getExecutePath());
		}
	}
}
