

package com.cloud.auth.endpoint;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.core.constant.PaginationConstants;
import com.cloud.common.core.constant.SecurityConstants;
import com.cloud.common.core.util.R;
import com.cloud.common.security.annotation.Inner;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.ConvertingCursor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ygnet
 * @date 2018/6/24
 * 删除token端点
 */
@RestController
@AllArgsConstructor
@RequestMapping("/token")
public class MicroTokenEndpoint {

	private static final String MICRO__ACCESS = SecurityConstants.MICRO_PREFIX + SecurityConstants.OAUTH_PREFIX + "access:";
	private final TokenStore tokenStore;
	private final RedisTemplate redisTemplate;
	private final CacheManager cacheManager;

	/**
	 * 认证页面
	 *
	 * @return ModelAndView
	 */
	@GetMapping("/login")
	public ModelAndView require() {
		return new ModelAndView("ftl/login");
	}

	/**
	 * 退出token
	 *
	 * @param authHeader Authorization
	 */
	@DeleteMapping("/logout")
	public R logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
		if (StrUtil.isBlank(authHeader)) {
			return R.builder()
					.code(CommonConstants.FAIL)
					.data(Boolean.FALSE)
					.msg("退出失败，token 为空").build();
		}

		String tokenValue = authHeader.replace("Bearer", "").trim();
		OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
		if (accessToken == null || StrUtil.isBlank(accessToken.getValue())) {
			return R.builder()
					.code(CommonConstants.FAIL)
					.data(Boolean.FALSE)
					.msg("退出失败，token 无效").build();
		}

		OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(accessToken);
		cacheManager.getCache("user_details")
				.evict(auth2Authentication.getName());
		tokenStore.removeAccessToken(accessToken);
		return new R<>(Boolean.TRUE);
	}

	/**
	 * 令牌管理调用
	 *
	 * @param token token
	 * @return
	 */
	@Inner
	@DeleteMapping("/{token}")
	public R<Boolean> delToken(@PathVariable("token") String token) {
		OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
		tokenStore.removeAccessToken(oAuth2AccessToken);
		return new R<>();
	}


	/**
	 * 查询token
	 *
	 * @param params 分页参数
	 * @return
	 */
	@Inner
	@PostMapping("/page")
	public R<Page> tokenList(@RequestBody Map<String, Object> params) {
		//根据分页参数获取对应数据
		List<String> pages = findKeysForPage(MICRO__ACCESS + "*", MapUtil.getInt(params, PaginationConstants.CURRENT)
				, MapUtil.getInt(params, PaginationConstants.SIZE));

		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		Page result = new Page(MapUtil.getInt(params, PaginationConstants.CURRENT), MapUtil.getInt(params, PaginationConstants.SIZE));
		result.setRecords(redisTemplate.opsForValue().multiGet(pages));
		result.setTotal(Long.valueOf(redisTemplate.keys(MICRO__ACCESS).size()));
		return new R<>(result);
	}


	private List<String> findKeysForPage(String patternKey, int pageNum, int pageSize) {
		ScanOptions options = ScanOptions.scanOptions().match(patternKey).build();
		RedisSerializer<String> redisSerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
		Cursor cursor = (Cursor) redisTemplate.executeWithStickyConnection(redisConnection -> new ConvertingCursor<>(redisConnection.scan(options), redisSerializer::deserialize));
		List<String> result = new ArrayList<>();
		int tmpIndex = 0;
		int startIndex = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		assert cursor != null;
		while (cursor.hasNext()) {
			if (tmpIndex >= startIndex && tmpIndex < end) {
				result.add(cursor.next().toString());
				tmpIndex++;
				continue;
			}
			if (tmpIndex >= end) {
				break;
			}
			tmpIndex++;
			cursor.next();
		}
		return result;
	}
}
