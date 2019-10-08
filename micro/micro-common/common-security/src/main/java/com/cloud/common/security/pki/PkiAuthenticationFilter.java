

package com.cloud.common.security.pki;

import cn.com.jit.cinas.authentication.credential.credentials.CertCredential;
import com.cloud.common.core.constant.SecurityConstants;
import com.cloud.common.security.util.X509Utils;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ygnet
 * @date 2018/1/9
 * PKI登录验证filter
 */
public class PkiAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    /**
     * 前端 传送证书内容的字段名称
     */
    private static final String SPRING_SECURITY_FORM_MOBILE_KEY = "pki";

    @Getter
    @Setter
    private boolean postOnly = true;
    @Getter
    @Setter
    private AuthenticationEventPublisher eventPublisher;
    @Getter
    @Setter
    private AuthenticationEntryPoint authenticationEntryPoint;
    /**
     * 前端证书传输加密秘钥
     */
    private static final String KEY = "ygnet";


    public PkiAuthenticationFilter() {
        super(new AntPathRequestMatcher(SecurityConstants.PKI_TOKEN_URL, "POST"));
    }

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) {
        if (postOnly && !request.getMethod().equals(HttpMethod.POST.name())) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        String pkiCode = request.getParameter(SPRING_SECURITY_FORM_MOBILE_KEY);
        if (null != pkiCode && "" != pkiCode) {
            CertCredential certCredential = X509Utils.getCertCredential("", pkiCode, new BASE64Encoder().encode(KEY.getBytes()));
            if (null != certCredential && null != certCredential.getCert()) {
                String subject = certCredential.getCert().getSubject();
                if (null != subject && "" != subject) {
                    int start = subject.indexOf("CN=") + 3;
                    int end = subject.indexOf(",", start);
                    String idValue = subject.substring(start, end);
                    pkiCode = idValue.split(" ")[1];
                    pkiCode = pkiCode.trim();

                }
            }


        }

        PkiAuthenticationToken pkiAuthenticationToken = new PkiAuthenticationToken(pkiCode);

        setDetails(request, pkiAuthenticationToken);

        Authentication authResult = null;
        try {
            authResult = this.getAuthenticationManager().authenticate(pkiAuthenticationToken);

            logger.debug("Authentication success: " + authResult);
            SecurityContextHolder.getContext().setAuthentication(authResult);

        } catch (Exception failed) {
            SecurityContextHolder.clearContext();
            logger.debug("Authentication request failed: " + failed);

            eventPublisher.publishAuthenticationFailure(new BadCredentialsException(failed.getMessage(), failed),
                    new PreAuthenticatedAuthenticationToken("access-token", "N/A"));

            try {
                authenticationEntryPoint.commence(request, response,
                        new UsernameNotFoundException(failed.getMessage(), failed));
            } catch (Exception e) {
                logger.error("authenticationEntryPoint handle error:{}", failed);
            }
        }

        return authResult;
    }


    private void setDetails(HttpServletRequest request,
                            PkiAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}

