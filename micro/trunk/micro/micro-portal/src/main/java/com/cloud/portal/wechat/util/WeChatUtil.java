package com.cloud.portal.wechat.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cloud.admin.api.entity.SysUser;
import com.cloud.common.core.http.HttpClientUtil;
import com.cloud.common.core.http.Result;
import com.cloud.common.core.http.entity.RequestParam;
import com.cloud.common.core.util.SpringContextHolder;
import com.cloud.portal.wechat.config.WeChatApiConfig;
import com.cloud.portal.wechat.entity.WeChatToken;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wengshij
 * @date Created in 2019/8/21 9:26
 * @description:
 * @modified By:wengshij
 */
@Slf4j
@UtilityClass
public class WeChatUtil {
    /**
     * 政务微信 令牌 缓存名称 -令牌缓存KEY值
     */
    private final String CACHE_NAME = "WE_CHAT_TOKEN", CACHE_KEY = "KEY_WC";

    /**
     * 问号 -并且- 等号
     */
    private final String WH_SYMBOL = "?", BQ_SYMBOL = "&", EQ_SYMBOL = "=";
    /**
     * 参数拼接默认值
     */
    private final String DEVELOPER_ID_PARAMS = "developerId", DEVELOPER_KEY_PARAMS = "developerKey",
            TOKEN_PARAMS = "token", APP_ID_PARAMS = "appId", A_CODE_PARAMS = "acode";
    /**
     * 返回参数获取到JSON key值
     */
    private final String CODE_JSON_KEY = "code", DATA_JSON_KEY = "data",
            AVATAR_KEY = "avatar",
            DEPARTMENT_KEY = "department",
            EXT_JH_KEY = "extJh",
            GENDER_KEY = "gender",
            JH_KEY = "jh", MOBILE_KEY = "mobile", NAME_KEY = "name";


    /**
     * 请求返回状态
     */
    private final String WE_CHAT_CODE_OK = "0";
    /**
     * 读取政务微信接口配置信息
     */
    private WeChatApiConfig weChatApiConfig = SpringContextHolder.getBean(WeChatApiConfig.class);
    private CacheManager cacheManager = SpringContextHolder.getBean(CacheManager.class);

    /**
     * 获取政务微信令牌信息
     *
     * @return 令牌信息
     */
    public String getToken() {
        String dKey = weChatApiConfig.getDeveloperKey(), dId = weChatApiConfig.getDeveloperId(), tokenUrl = weChatApiConfig.getTokenUrl();
        if (StringUtils.isBlank(dKey) || StringUtils.isBlank(dId) || StringUtils.isBlank(tokenUrl)) {
            throw new RuntimeException(String.format("接口参数缺失、政务微信令牌获取失败,ex={developerKey:%s,developerId:%s,tokenUrl:%s}", dKey, dId, tokenUrl));
        }
        Cache cache = cacheManager.getCache(CACHE_NAME);
        Object object = null != cache ? cache.get(CACHE_KEY) : null;
        if (null != object) {
            WeChatToken weChatToken = (WeChatToken) ((SimpleValueWrapper) object).get();

            boolean isTimeOut = ((System.currentTimeMillis() - weChatToken.getExpiresTime()) / 1000) > (weChatToken.getExpiresIn() * 0.9);
            if (isTimeOut) {
                cache.clear();
                return getToken();
            } else {
                return weChatToken.getToken();
            }
        } else {
            Map<String, String> map = new HashMap<>(2);
            map.put(DEVELOPER_ID_PARAMS, dId);
            map.put(DEVELOPER_KEY_PARAMS, dKey);
            Result result = HttpClientUtil.get(new RequestParam(getUrl(tokenUrl, map)));
            if (HttpStatus.SC_OK == result.getCode()) {
                JSONObject jsonObject = JSONUtil.parseObj(result.getData());
                String code = jsonObject.getStr(CODE_JSON_KEY);
                if (StringUtils.isNotBlank(code) && WE_CHAT_CODE_OK.equals(code)) {
                    WeChatToken weChatToken = WeChatToken.initToken(jsonObject.getJSONObject(DATA_JSON_KEY));
                    if (weChatToken != null && StringUtils.isNotBlank(weChatToken.getToken())) {
                        cacheManager.getCache(CACHE_NAME).put(CACHE_KEY, weChatToken);
                        return weChatToken.getToken();
                    }
                }
                throw new RuntimeException(String.format("令牌请求失败,ex=%s", result.getData()));
            } else {
                throw new RuntimeException(String.format("令牌请求失败,ex={%s}", result.getMsg()));
            }
        }
    }

    /**
     * 初始化地址参数
     *
     * @param url
     * @param searchMap
     * @return
     */
    private String getUrl(String url, Map<String, String> searchMap) {
        if (null != searchMap && StringUtils.isNotBlank(url)) {
            StringBuffer sb = new StringBuffer();
            searchMap.forEach((key, value) -> {
                sb.append(BQ_SYMBOL).append(key).append(EQ_SYMBOL).append(value);
            });
            if (url.contains(WH_SYMBOL)) {
                url = url + sb.toString();
            } else {
                url = url + sb.toString().replaceFirst(BQ_SYMBOL, WH_SYMBOL);
            }
        }
        return url;
    }

    /**
     * 获取政务微信当前用户信息
     *
     * @param aCode 唯一凭证信息
     * @return
     */
    public SysUser getUserInfo(String aCode) {
        String appId = weChatApiConfig.getAppId(), userInfoUrl = weChatApiConfig.getUserInfoUrl(), token = getToken();
        boolean loseParams = StringUtils.isBlank(aCode) || StringUtils.isBlank(userInfoUrl) || StringUtils.isBlank(token) || StringUtils.isBlank(appId);
        if (loseParams) {
            throw new RuntimeException(String.format("获取政务微信当前用户参数丢失,ex={appId:%s,userInfoUrl:%s,aCode:%s}", appId, userInfoUrl, aCode));
        }
        Map<String, String> paramsMap = new HashMap<>(3);
        paramsMap.put(TOKEN_PARAMS, token);
        paramsMap.put(APP_ID_PARAMS, appId);
        paramsMap.put(A_CODE_PARAMS, aCode);
        Result result = HttpClientUtil.get(new RequestParam(getUrl(userInfoUrl, paramsMap)));
        if (HttpStatus.SC_OK == result.getCode()) {
            JSONObject userJson = JSONUtil.parseObj(result.getData());
            String statusCode = userJson.getStr(CODE_JSON_KEY);
            if (StringUtils.isNotBlank(statusCode) && WE_CHAT_CODE_OK.equals(statusCode)) {
                JSONObject userData = userJson.getJSONObject(DATA_JSON_KEY);
                if (null != userData) {
                    String jh = userData.getStr(JH_KEY);
                    if (StringUtils.isNotBlank(jh)) {
                        SysUser sysUser = new SysUser();
                        sysUser.setIdcard(jh);
                        sysUser.setPoliceNumber(userData.getStr(EXT_JH_KEY));
                        sysUser.setAvatar(userData.getStr(AVATAR_KEY));
                        sysUser.setDeptId(userData.getStr(DEPARTMENT_KEY));
                        sysUser.setSex(userData.getStr(GENDER_KEY));
                        sysUser.setName(userData.getStr(NAME_KEY));
                        sysUser.setPhone(userData.getStr(MOBILE_KEY));
                        return sysUser;
                    }
                }
            }
            throw new RuntimeException(String.format("获取用户信息失败,ex=%s", result.getData()));
        } else {
            throw new RuntimeException(String.format("用户信息请求失败,ex={%s}", result.getMsg()));
        }


    }

}
