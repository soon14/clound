

package com.cloud.common.security.pki;

import cn.com.jit.cinas.authentication.credential.credentials.CertCredential;
import com.cloud.admin.api.entity.SysUser;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.core.constant.SecurityConstants;
import com.cloud.common.core.util.SpringContextHolder;
import com.cloud.common.security.service.MicroUserDetailsService;
import com.cloud.common.security.util.X509Utils;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
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
 * 和其他第三方登录校验信息验证
 * 均是根据身份证校验
 */
public class PkiAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    /**
     * 前端 传送证书内容的字段名称
     */
    private static final String SPRING_SECURITY_FORM_MOBILE_KEY = "pki";
    /**
     * 登录类型
     * PKI登录-pki
     * 微信登录-wecat
     */
    private static final String LOGIN_TYPE_NAME = "type";


    /**
     * 登录类型 、PKI登录、政务微信CODE登录 、身份证登录
     */
    private static final String[] LOGIN_TYPE = {"pki", "weChat", "idCode"};
    @Getter
    @Setter
    private boolean postOnly = true;
    @Getter
    @Setter
    private AuthenticationEventPublisher eventPublisher;
    @Getter
    @Setter
    private AuthenticationEntryPoint authenticationEntryPoint;

    private MicroUserDetailsService userDetailsService = SpringContextHolder.getBean(MicroUserDetailsService.class);
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
        String loginType = request.getParameter(LOGIN_TYPE_NAME);
        String idCard = null;
        if (StringUtils.isNotBlank(loginType) && loginType.equalsIgnoreCase(LOGIN_TYPE[0])) {
            String pkiCode = request.getParameter(SPRING_SECURITY_FORM_MOBILE_KEY);
            if (StringUtils.isNotBlank(pkiCode)) {
                CertCredential certCredential = X509Utils.getCertCredential("", pkiCode, new BASE64Encoder().encode(KEY.getBytes()));
                if (null != certCredential && null != certCredential.getCert()) {
                    String subject = certCredential.getCert().getSubject();
                    if (null != subject && "" != subject) {
                        int start = subject.indexOf("CN=") + 3;
                        int end = subject.indexOf(",", start);
                        String idValue = subject.substring(start, end);
                        pkiCode = idValue.split(" ")[1];
                        idCard = pkiCode.trim();

                    }
                }
            }
        } else if (StringUtils.isNotBlank(loginType) && loginType.equalsIgnoreCase(LOGIN_TYPE[1])) {
            String aCode = request.getParameter(CommonConstants.SECURITY_CODE);
            SysUser sysUser = userDetailsService.loadWeChatUserByACode(aCode);
            if (null != sysUser && StringUtils.isNotBlank(sysUser.getIdcard())) {
                idCard = sysUser.getIdcard();
            }
        } else if (StringUtils.isNotBlank(loginType) && loginType.equalsIgnoreCase(LOGIN_TYPE[2])) {
            idCard = request.getParameter(CommonConstants.SECURITY_CODE);
        } else {
            throw new AuthenticationServiceException(
                    "Authentication request failed:" + "无法获取用户验证信息");
        }
        PkiAuthenticationToken pkiAuthenticationToken = new PkiAuthenticationToken(idCard);
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

