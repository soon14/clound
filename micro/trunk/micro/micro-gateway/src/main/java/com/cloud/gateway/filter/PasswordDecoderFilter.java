

package com.cloud.gateway.filter;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.http.HttpUtil;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.core.constant.SecurityConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author ygnet
 * @date 2018/7/15
 * 密码解密工具类
 */
@Slf4j
@Component
public class PasswordDecoderFilter extends AbstractGatewayFilterFactory {
    private static final String PASSWORD = "password";

    private static final String KEY_ALGORITHM = "AES";
    @Value("${security.encode.key:makebysunsineboy}")
    private String encodeKey;

    @SneakyThrows
    private static String decryptAES(String data, String pass) {
        AES aes = new AES(Mode.CBC, Padding.NoPadding,
                new SecretKeySpec(pass.getBytes(), KEY_ALGORITHM),
                new IvParameterSpec(pass.getBytes()));
        byte[] result = aes.decrypt(Base64.decode(data.getBytes(StandardCharsets.UTF_8)));
        return new String(result, StandardCharsets.UTF_8);
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 不是登录请求，直接向下执行
            boolean isLoginRequest = !StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), SecurityConstants.OAUTH_TOKEN_URL)
                    && !StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), SecurityConstants.PKI_TOKEN_URL);
            if (isLoginRequest) {
                return chain.filter(exchange);
            }

            URI uri = exchange.getRequest().getURI();
            String queryParam = uri.getRawQuery();
            Map<String, String> paramMap = HttpUtil.decodeParamMap(queryParam, CharsetUtil.UTF_8);

            String password = paramMap.get(PASSWORD);
            if (StrUtil.isNotBlank(password)) {
                try {
                    password = decryptAES(password, encodeKey);
                } catch (Exception e) {
                    log.error("密码解密失败:{}", password);
                    return Mono.error(e);
                }
                paramMap.put(PASSWORD, password.trim());
            }
            //解密前端传送的秘钥代码值
            String securityCode = paramMap.get(CommonConstants.SECURITY_CODE);
            if (StrUtil.isNotBlank(securityCode)) {
                try {
                    securityCode = decryptAES(securityCode, encodeKey);
                } catch (Exception e) {
                    log.error("秘钥代码值解密失败:{}", securityCode);
                    return Mono.error(e);
                }
                paramMap.put(CommonConstants.SECURITY_CODE, securityCode.trim());
            }


            URI newUri = UriComponentsBuilder.fromUri(uri)
                    .replaceQuery(HttpUtil.toParams(paramMap))
                    .build(true)
                    .toUri();

            ServerHttpRequest newRequest = exchange.getRequest().mutate().uri(newUri).build();
            return chain.filter(exchange.mutate().request(newRequest).build());
        };
    }
}
