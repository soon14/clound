
package com.cloud.quartz.util;


import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.cloud.common.core.util.SpringContextHolder;
import com.cloud.quartz.constant.enums.JobTypeQuartzEnum;
import com.cloud.quartz.constant.enums.QuartzEnum;
import com.cloud.quartz.entity.SysJob;
import com.cloud.quartz.entity.SysJobLog;
import com.cloud.quartz.event.SysJobLogEvent;
import com.cloud.quartz.exception.TaskException;
import com.cloud.quartz.service.SysJobService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author wengshij
 * @date Created in 2019/6/25 9:51
 * @description: 定时任务反射工具类
 * @modified By:wengshij
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class TaskInvokeUtil {
	private final ApplicationEventPublisher publisher;

	@Autowired
	private SysJobService sysJobService;

	@SneakyThrows
	public void invokeMethod(SysJob sysJob, Trigger trigger) {

		ITaskInvoke iTaskInvoke;
		//执行开始时间
		long startTime;
		//执行结束时间
		long endTime;
		//获取执行开始时间
		startTime = System.currentTimeMillis();
		//更新定时任务表内的状态、执行时间、上次执行时间、下次执行时间等信息
		SysJob updateSysjob = new SysJob();
		updateSysjob.setJobId(sysJob.getJobId());
		//日志
		SysJobLog sysJobLog = new SysJobLog();
		sysJobLog.setJobLogId(IdUtil.randomUUID());
		sysJobLog.setJobId(sysJob.getJobId());
		sysJobLog.setJobName(sysJob.getJobName());
		sysJobLog.setJobGroup(sysJob.getJobGroup());
		sysJobLog.setJobOrder(sysJob.getJobOrder());
		sysJobLog.setJobType(sysJob.getJobType());
		sysJobLog.setExecutePath(sysJob.getExecutePath());
		sysJobLog.setClassName(sysJob.getClassName());
		sysJobLog.setMethodName(sysJob.getMethodName());
		sysJobLog.setMethodParamsValue(sysJob.getMethodParamsValue());
		sysJobLog.setCronExpression(sysJob.getCronExpression());
		sysJobLog.setCreateTime(LocalDateTime.now());
		try {
			if (StrUtil.isEmpty(sysJob.getJobType())) {
				log.info("定时任务类型无对应反射方式，反射类型为空");
				throw new TaskException("定时任务类型无对应反射方式，反射类型为空");
			}
			if (StrUtil.isNotEmpty(sysJob.getJobType()) && JobTypeQuartzEnum.JAVA.getType()
					.equals(sysJob.getJobType())) {
				iTaskInvoke = SpringContextHolder.getBean("javaClassTaskInvoke");
				iTaskInvoke.invokeMethod(sysJob);
			} else if (StrUtil.isNotEmpty(sysJob.getJobType()) && JobTypeQuartzEnum.SPRING_BEAN.getType()
					.equals(sysJob.getJobType())) {
				iTaskInvoke = SpringContextHolder.getBean("springBeanTaskInvoke");
				iTaskInvoke.invokeMethod(sysJob);
			} else if (StrUtil.isNotEmpty(sysJob.getJobType()) && JobTypeQuartzEnum.REST.getType()
					.equals(sysJob.getJobType())) {
				iTaskInvoke = SpringContextHolder.getBean("restTaskInvoke");
				iTaskInvoke.invokeMethod(sysJob);
			} else if (StrUtil.isNotEmpty(sysJob.getJobType()) && JobTypeQuartzEnum.JAR.getType()
					.equals(sysJob.getJobType())) {
				iTaskInvoke = SpringContextHolder.getBean("jarTaskInvoke");
				iTaskInvoke.invokeMethod(sysJob);
			} else {
				log.info("定时任务类型无对应反射方式，反射类型:{}", sysJob.getJobType());
				throw new TaskException("");
			}
			//获取执行结束时间
			endTime = System.currentTimeMillis();
			sysJobLog.setJobMessage(QuartzEnum.JOB_LOG_STATUS_SUCCESS.getDescription());
			sysJobLog.setJobLogStatus(QuartzEnum.JOB_LOG_STATUS_SUCCESS.getType());
			sysJobLog.setExecuteTime(String.valueOf(endTime - startTime));
			//任务表信息更新
			updateSysjob.setJobExecuteStatus(QuartzEnum.JOB_LOG_STATUS_SUCCESS.getType());
			updateSysjob.setStartTime(trigger.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			updateSysjob.setPreviousTime(trigger.getPreviousFireTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			updateSysjob.setNextTime(trigger.getNextFireTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		} catch (Throwable e) {
			//获取执行结束时间
			endTime = System.currentTimeMillis();
			log.error("定时任务执行失败，任务名称：{}；任务组名：{}，cron执行表达式：{}，执行时间：{}", sysJob.getJobName(), sysJob.getJobGroup(), sysJob.getCronExpression(), new Date());
			sysJobLog.setJobMessage(QuartzEnum.JOB_LOG_STATUS_FAIL.getDescription());
			sysJobLog.setJobLogStatus(QuartzEnum.JOB_LOG_STATUS_FAIL.getType());
			sysJobLog.setExecuteTime(String.valueOf(endTime - startTime));
			sysJobLog.setExceptionInfo(StrUtil.sub(e.getMessage(), 0, 2000));
			//任务表信息更新
			updateSysjob.setJobExecuteStatus(QuartzEnum.JOB_LOG_STATUS_FAIL.getType());
			updateSysjob.setStartTime(trigger.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			updateSysjob.setPreviousTime(trigger.getPreviousFireTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			updateSysjob.setNextTime(trigger.getNextFireTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		} finally {
			publisher.publishEvent(new SysJobLogEvent(sysJobLog));
			sysJobService.updateById(updateSysjob);
		}
	}
}
