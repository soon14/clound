

package com.cloud.common.log.util;

import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import com.cloud.admin.api.annotation.LogField;
import com.cloud.admin.api.entity.SysLog;
import com.cloud.admin.api.feign.RemoteDictService;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.core.util.SpringContextHolder;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 系统日志工具类
 *
 * @author L.cm
 */
@UtilityClass
public class SysLogUtils {
    RemoteDictService dictService = SpringContextHolder.getBean(RemoteDictService.class);

    public SysLog getSysLog(Object[] args, String[] parmNames) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        SysLog sysLog = new SysLog();
        sysLog.setCreateBy(Objects.requireNonNull(getUsername()));
        sysLog.setType(CommonConstants.STATUS_NORMAL);
        sysLog.setRemoteAddr(HttpUtil.getClientIP(request));
        sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
        sysLog.setMethod(request.getMethod());
        sysLog.setUserAgent(request.getHeader("user-agent"));
        /* sysLog.setParams(HttpUtil.toParams(request.getParameterMap()));*/
        sysLog.setParams(initParams(args, parmNames));
        sysLog.setServiceId(getClientId());
        return sysLog;
    }

    /**
     * 通过注解反射获取参数信息、封装为字符串
     *
     * @param args
     * @param parmNames
     * @return
     */
    private String initParams(Object[] args, String[] parmNames) {
        StringBuilder params = new StringBuilder(" ");
        String dh = ",", mh = "：", zkh = "【", ykh = "】";
        try {
            for (int i = 0; i < args.length; i++) {
                boolean ordinaryType = (args[i] instanceof Integer ||
                        args[i] instanceof String ||
                        args[i] instanceof Double ||
                        args[i] instanceof Float ||
                        args[i] instanceof Long ||
                        args[i] instanceof Boolean);
                if (ordinaryType) {
                    params.append(zkh).append(parmNames[i]).append(mh).append(args[i].toString()).append(ykh).append(dh);
                } else if (args[i] instanceof Date) {
                    params.append(zkh).append(parmNames[i]).append(mh).append(args[i].toString()).append(ykh).append(dh);
                } else if (args[i] instanceof Map) {
                    Map map = (Map) args[i];
                    for (Object key : map.entrySet()) {
                        if (null != map.get(key)) {
                            params.append(zkh).append(key.toString()).append(mh).append(map.get(key)).append(ykh).append(dh);
                        }
                    }
                } else if (args[i] instanceof List) {
                    List list = (List) args[i];
                    params.append(zkh).append(parmNames[i]).append(mh).append(StringUtils.join(list.toArray(), ",")).append(ykh).append(dh);
                } else {
                    Field[] fs = args[i].getClass().getSuperclass().getDeclaredFields();
                    for (Field field : fs) {
                        field.setAccessible(true);
                        LogField logField = field.getAnnotation(LogField.class);
                        Object value = field.get(args[i]);
                        boolean hasValue = null != logField && null != value;
                        if (hasValue) {
                            if (StringUtils.isNotBlank(logField.dictType())) {
                                value = dictService.getDictLabel(value.toString(), logField.dictType(), value.toString());
                            }
                            String title = StringUtils.isBlank(logField.title()) ? field.getName() : logField.title();
                            params.append(zkh).append(title).append(mh).append(value).append(ykh).append(dh);
                        }
                    }
                }
            }
            if (params.toString().endsWith(dh)) {
                params.deleteCharAt(params.length() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return params.toString();

    }

    /**
     * 获取客户端
     *
     * @return clientId
     */
    private String getClientId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
            return auth2Authentication.getOAuth2Request().getClientId();
        }
        return null;
    }

    /**
     * 获取用户名称
     *
     * @return username
     */
    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getName();
    }

}
