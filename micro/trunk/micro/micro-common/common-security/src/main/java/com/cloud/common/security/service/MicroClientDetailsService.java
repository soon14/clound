

package com.cloud.common.security.service;

import com.cloud.common.core.constant.SecurityConstants;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @author ygnet
 * @date 2018/10/22
 * <p>
 * see JdbcClientDetailsService
 */
public class MicroClientDetailsService extends JdbcClientDetailsService {

	public MicroClientDetailsService(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 重写原生方法支持redis缓存
	 *
	 * @param clientId
	 * @return
	 * @throws InvalidClientException
	 */
	@Override
	@SneakyThrows
	@Cacheable(value = SecurityConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
	public ClientDetails loadClientByClientId(String clientId) {
		return super.loadClientByClientId(clientId);
	}
}
