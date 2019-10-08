
package com.cloud.quartz.util;

import cn.hutool.core.util.StrUtil;
import com.cloud.common.core.util.SpringContextHolder;
import com.cloud.quartz.constant.enums.QuartzEnum;
import com.cloud.quartz.entity.SysJob;
import com.cloud.quartz.exception.TaskException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * @author wengshij
 * @date Created in 2019/6/25 9:51
 * @description: spring bean 反射实现
 * @modified By:wengshij
 */
@Component("springBeanTaskInvoke")
@Slf4j
public class SpringBeanTaskInvoke implements ITaskInvoke {
    @Override
    public void invokeMethod(SysJob sysJob) throws TaskException {
        Object target;
        Method method;
        Object returnValue;
        //通过Spring上下文去找 也有可能找不到
        target = SpringContextHolder.getBean(sysJob.getClassName());
        try {
            if (StrUtil.isNotEmpty(sysJob.getMethodParamsValue())) {
                method = target.getClass().getDeclaredMethod(sysJob.getMethodName(), String.class);
                ReflectionUtils.makeAccessible(method);
                returnValue = method.invoke(target, sysJob.getMethodParamsValue());
            } else {
                method = target.getClass().getDeclaredMethod(sysJob.getMethodName());
                ReflectionUtils.makeAccessible(method);
                returnValue = method.invoke(target);
            }
            if (StrUtil.isEmpty(returnValue.toString()) || QuartzEnum.JOB_LOG_STATUS_FAIL.getType()
                    .equals(returnValue.toString())) {
                log.error("定时任务springBeanTaskInvok异常,执行任务：{}", sysJob.getClassName());
                throw new TaskException("定时任务springBeanTaskInvok业务执行失败,任务：" + sysJob.getClassName());
            }
        } catch (NoSuchMethodException e) {
            log.error("定时任务spring bean反射异常方法未找到,执行任务：{}", sysJob.getClassName());
            throw new TaskException("定时任务spring bean反射异常方法未找到,执行任务：" + sysJob.getClassName());
        } catch (IllegalAccessException e) {
            log.error("定时任务spring bean反射异常,执行任务：{}", sysJob.getClassName());
            throw new TaskException("定时任务spring bean反射异常,执行任务：" + sysJob.getClassName());
        } catch (InvocationTargetException e) {
            log.error("定时任务spring bean反射执行异常,执行任务：{}", sysJob.getClassName());
            throw new TaskException("定时任务spring bean反射执行异常,执行任务：" + sysJob.getClassName());
        }
    }
}
