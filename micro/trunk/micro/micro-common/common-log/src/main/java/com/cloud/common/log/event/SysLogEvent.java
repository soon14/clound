

package com.cloud.common.log.event;

import com.cloud.admin.api.entity.SysLog;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ygnet
 * 系统日志事件
 */
@Getter
@AllArgsConstructor
public class SysLogEvent {
	private final SysLog sysLog;
}
