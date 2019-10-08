

package com.cloud.auth.handler;

import com.cloud.common.security.handler.AbstractAuthenticationSuccessEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author ygnet
 * @date 2018/10/8
 */
@Slf4j
@Component
public class MicroAuthenticationSuccessEventHandler extends AbstractAuthenticationSuccessEventHandler {

	/**
	 * 处理登录成功方法
	 * <p>
	 * 获取到登录的authentication 对象
	 *
	 * @param authentication 登录对象
	 */
	@Override
	public void handle(Authentication authentication) {
		log.info("用户：{} 登录成功", authentication.getPrincipal());
	}
}
