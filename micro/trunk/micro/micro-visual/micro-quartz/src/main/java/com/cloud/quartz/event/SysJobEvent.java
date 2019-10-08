
package com.cloud.quartz.event;

import com.cloud.quartz.entity.SysJob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.quartz.Trigger;

/**
 * @author wengshij
 * @date Created in 2019/6/25 9:51
 * @description: 定时任务多线程事件
 * @modified By:wengshij
 */
@Getter
@AllArgsConstructor
public class SysJobEvent {

	private final SysJob sysJob;

	private final Trigger trigger;
}
