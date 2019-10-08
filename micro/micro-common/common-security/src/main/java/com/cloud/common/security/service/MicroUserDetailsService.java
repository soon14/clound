
package com.cloud.common.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author ygnet
 * @date 2018/8/15
 */
public interface MicroUserDetailsService extends UserDetailsService {

	/**
	 * 根据社交登录code 登录
	 *
	 * @param code TYPE@CODE
	 * @return UserDetails
	 * @throws UsernameNotFoundException
	 */
	UserDetails loadUserBySocial(String code) throws UsernameNotFoundException;

	/**
	 * 根据PKI中获取到的身份证号码进行登录
	 * @param idCard
	 * @return
	 * @throws UsernameNotFoundException
	 */
	UserDetails loadUserByIdCard(String idCard) throws UsernameNotFoundException;
}
