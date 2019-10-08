package com.cloud.cas.service.impl;

import com.cloud.cas.contant.CasConstant;
import com.cloud.cas.entity.CasUser;
import com.cloud.cas.service.CasService;
import com.dragonsoft.dcuc.common.util.PropUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author wengshij
 * @date Created in 2019/8/28 17:31
 * @description:
 * @modified By:wengshij
 */
@Service
public class CasServiceImpl implements CasService {
    @Override
    public String getUserInfo(HttpServletRequest request) {
        try {
            Object object = request.getSession().getAttribute(CasConstant.SESSION_USER_PARAMS);
            CasUser casUser = (CasUser) object;
            return casUser.getIdCode();
        } catch (Exception var3) {
            throw new SecurityException("获取用户信息异常", var3);
        }
    }

    @Override
    public String loginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        PropUtils propUtils = PropUtils.getInstance();
        String logoutUrl = propUtils.getConfigItem(CasConstant.CAS_SERVER_URL) + CasConstant.LOGIN_OUT_URL;
        String redirectUrl = request.getParameter(CasConstant.REDIRECT_URL);
        if (StringUtils.isEmpty(redirectUrl)) {
            redirectUrl = propUtils.getConfigItem(CasConstant.CAS_CLIENT_URL);
        }
        logoutUrl = logoutUrl + CasConstant.SERVICE_URL + redirectUrl;
        return logoutUrl;
    }
}
