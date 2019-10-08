

package com.cloud.admin.controller;

import com.cloud.admin.api.feign.RemoteTokenService;
import com.cloud.common.core.constant.SecurityConstants;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ygnet
 * @date 2018/9/4
 * getTokenPage 管理
 */
@RestController
@AllArgsConstructor
@RequestMapping("/token")
@Api(value = "token", description = "令牌管理模块")
public class TokenController {
	private final RemoteTokenService remoteTokenService;

	/**
	 * 分页token 信息
	 *
	 * @param params 参数集
	 * @return token集合
	 */
	@GetMapping("/page")
	@SysLog("TOKEN查询")
	public R getTokenPage(@RequestParam Map<String, Object> params) {
		return remoteTokenService.getTokenPage(params, SecurityConstants.FROM_IN);
	}

	/**
	 * 删除
	 *
	 * @param token getTokenPage
	 * @return success/false
	 */
	@SysLog("删除用户token")
	@DeleteMapping("/{token}")
	@PreAuthorize("@pms.hasPermission('sys_token_del')")
	public R removeById(@PathVariable String token) {
		return remoteTokenService.removeTokenById(token, SecurityConstants.FROM_IN);
	}
}
