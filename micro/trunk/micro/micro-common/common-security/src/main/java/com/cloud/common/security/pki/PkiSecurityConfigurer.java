

package com.cloud.common.security.pki;

import com.cloud.common.security.component.ResourceAuthExceptionEntryPoint;
import com.cloud.common.security.service.MicroUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author ygnet
 * @date 2018/8/5
 * PKI证书登录配置入口
 */
@Getter
@Setter
@Component
public class PkiSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private AuthenticationEventPublisher defaultAuthenticationEventPublisher;
	private AuthenticationSuccessHandler pkiLoginSuccessHandler;
	private MicroUserDetailsService userDetailsService;

	@Override
	public void configure(HttpSecurity http) {
		PkiAuthenticationFilter pkiAuthenticationFilter = new PkiAuthenticationFilter();
		pkiAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		pkiAuthenticationFilter.setAuthenticationSuccessHandler(pkiLoginSuccessHandler);
		pkiAuthenticationFilter.setEventPublisher(defaultAuthenticationEventPublisher);
		pkiAuthenticationFilter.setAuthenticationEntryPoint(new ResourceAuthExceptionEntryPoint(objectMapper));

		PkiAuthenticationProvider pkiAuthenticationProvider = new PkiAuthenticationProvider();
		pkiAuthenticationProvider.setUserDetailsService(userDetailsService);
		http.authenticationProvider(pkiAuthenticationProvider)
			.addFilterAfter(pkiAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
