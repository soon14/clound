package com.cloud.api.token.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cloud.api.common.constant.ApiConstant;
import com.cloud.api.common.exception.ApiException;
import com.cloud.api.common.exception.HttpApiErrorCode;
import com.cloud.api.token.mapper.ApiToken;
import com.cloud.common.core.http.HttpClientUtil;
import com.cloud.common.core.http.Result;
import com.cloud.common.core.http.entity.RequestParam;
import com.cloud.common.core.util.SpringContextHolder;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wengshij
 * @date Created in 2019/8/8 15:28
 * @description:令牌获取工具类
 * @modified By:wengshij
 */
@Slf4j
@UtilityClass
public class ApiTokenUtil {

    private CacheManager cacheManager = SpringContextHolder.getBean(CacheManager.class);
    ;

    /**
     * 获取Token信息
     *
     * @param url
     * @param appKey    用户凭证中的 AppKey
     * @param secretKey 用户凭证中的 SecretKey
     * @return TokenInfo Token信息
     */
    public ApiToken defaultGetToken(String url, String appKey, String secretKey) {
        ApiToken apiToken;
        if (StringUtils.isNotBlank(appKey)) {
            Cache cache = cacheManager.getCache(ApiConstant.TOKEN_CACHE_NAME);
            Object object = null != cache ? cache.get(ApiConstant.TOKEN_PREFIX + appKey) : null;
            if (null != object) {
                apiToken = (ApiToken) ((SimpleValueWrapper) object).get();
                //判断令牌是否过期
                boolean isTimeOut = ((System.currentTimeMillis() - apiToken.getExpiresTime()) / 1000) > (apiToken.getExpiresIn() * 0.9);
                if (isTimeOut) {
                    cacheManager.getCache(ApiConstant.TOKEN_CACHE_NAME).clear();
                    apiToken = defaultGetToken(url, appKey, secretKey);
                    if (null != apiToken) {
                        apiToken.setExpiresTime(System.currentTimeMillis());
                        cacheManager.getCache(ApiConstant.TOKEN_CACHE_NAME).put(ApiConstant.TOKEN_PREFIX + appKey, apiToken);
                    }
                }
                return apiToken;
            }
        }
        // 添加请求体内容
        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair(ApiConstant.GRANT_TYPE, ApiConstant.CLIENT_CREDENTIALS));
        pairs.add(new BasicNameValuePair(ApiConstant.CLIENT_ID, appKey));
        pairs.add(new BasicNameValuePair(ApiConstant.CLIENT_SECRET, secretKey));
        //默认请求头
        List<Header> headerList = new ArrayList<>(1);
        headerList.add(new BasicHeader(HTTP.CONTENT_TYPE, ApiConstant.URLENCODED_TYPE));
        apiToken = postRequest(new RequestParam(url, pairs, headerList));
        if (null != apiToken) {
            apiToken.setExpiresTime(System.currentTimeMillis());
            cacheManager.getCache(ApiConstant.TOKEN_CACHE_NAME).put(ApiConstant.TOKEN_PREFIX + appKey, apiToken);
        }
        return apiToken;
    }

    /**
     * 刷新AccessToken有效期
     *
     * @param url          token刷新地址
     * @param appKey       用户凭证中的 AppKey
     * @param secretKey    用户凭证中的 SecretKey
     * @param refreshToken Token信息中用于刷新有效期的 refresh_token
     * @return TokenInfo Token信息
     */
    public ApiToken defaultRefreshToken(String url, String appKey, String secretKey, String refreshToken) {
        // 添加请求体内容

        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair(ApiConstant.GRANT_TYPE, ApiConstant.REFRESH_TOKEN));
        pairs.add(new BasicNameValuePair(ApiConstant.CLIENT_ID, appKey));
        pairs.add(new BasicNameValuePair(ApiConstant.CLIENT_SECRET, secretKey));
        pairs.add(new BasicNameValuePair(ApiConstant.REFRESH_TOKEN, refreshToken));
        //默认请求头
        List<Header> headerList = new ArrayList<>(1);
        headerList.add(new BasicHeader(HTTP.CONTENT_TYPE, ApiConstant.URLENCODED_TYPE));
        return postRequest(new RequestParam(url, pairs, headerList));
    }

    /**
     * POST 请求获取token
     *
     * @param tokenParam
     * @return
     * @throws HttpException
     */
    public ApiToken postRequest(RequestParam tokenParam) {
        ApiToken apiToken;
        Result result = HttpClientUtil.post(tokenParam);
        if (null != result && HttpStatus.SC_OK == result.getCode()) {
            apiToken = JSONUtil.toBean(result.getData().toString(), ApiToken.class);
        } else {
            throw ApiException.asApiException(HttpApiErrorCode.TOKEN_EXCEPTION, result.getMsg());
        }
        return apiToken;
    }



}
