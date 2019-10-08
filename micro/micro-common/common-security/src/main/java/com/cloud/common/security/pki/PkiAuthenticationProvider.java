

package com.cloud.common.security.pki;

import com.cloud.common.security.component.MicroPreAuthenticationChecks;
import com.cloud.common.security.service.MicroUserDetailsService;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * @author ygnet
 * @date 2018/8/5
 * 手机登录校验逻辑
 * 验证码登录、社交登录
 */
@Slf4j
public class PkiAuthenticationProvider implements AuthenticationProvider {
	private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	private UserDetailsChecker detailsChecker = new MicroPreAuthenticationChecks();

	@Getter
	@Setter
	private MicroUserDetailsService userDetailsService;

	@Override
	@SneakyThrows
	public Authentication authenticate(Authentication authentication) {
		PkiAuthenticationToken mobileAuthenticationToken = (PkiAuthenticationToken) authentication;

		String principal = mobileAuthenticationToken.getPrincipal().toString();
		UserDetails userDetails = userDetailsService.loadUserByIdCard(principal);
		if (userDetails == null) {
			log.debug("Authentication failed: no credentials provided");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.noopBindAccount",
					"Noop Bind Account"));
		}

		// 检查账号状态
		detailsChecker.check(userDetails);

		PkiAuthenticationToken authenticationToken = new PkiAuthenticationToken(userDetails, userDetails.getAuthorities());
		authenticationToken.setDetails(mobileAuthenticationToken.getDetails());
		return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return PkiAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
