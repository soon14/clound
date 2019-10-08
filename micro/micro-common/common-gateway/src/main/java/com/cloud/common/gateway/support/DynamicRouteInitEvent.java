

package com.cloud.common.gateway.support;

import org.springframework.context.ApplicationEvent;

/**
 * @author ygnet
 * @date 2018/11/5
 * <p>
 * 路由初始化事件
 */
public class DynamicRouteInitEvent extends ApplicationEvent {
	public DynamicRouteInitEvent(Object source) {
		super(source);
	}
}
