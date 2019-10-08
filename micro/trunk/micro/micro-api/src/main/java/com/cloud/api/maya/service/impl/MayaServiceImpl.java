package com.cloud.api.maya.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.api.common.config.MayaApiConfig;
import com.cloud.api.common.constant.JsonKeyConstant;
import com.cloud.api.common.entity.MethodType;
import com.cloud.api.common.entity.RestParam;
import com.cloud.api.common.exception.ApiException;
import com.cloud.api.common.exception.HttpApiErrorCode;
import com.cloud.api.maya.mapper.Maya;
import com.cloud.api.maya.service.MayaService;
import com.cloud.api.token.mapper.ApiToken;
import com.cloud.api.token.util.ApiTokenUtil;
import com.cloud.common.core.http.HttpClientUtil;
import com.cloud.common.core.http.JsonMapper;
import com.cloud.common.core.http.Result;
import com.cloud.common.core.http.entity.RequestParam;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wengshij
 * @date Created in 2019/8/10 15:19
 * @description:美亚接口实现层
 * @modified By:wengshij
 */
@Service
public class MayaServiceImpl implements MayaService {

    @Autowired
    private MayaApiConfig mayaApiConfig;

    private final String REQUEST_FLAG_REST = "1";
    private final String REQUEST_FLAG_OTHER = "0";

    @Override
    public IPage<Map> findPage(Page page, Maya maya) {
        if (null == maya || StringUtils.isBlank(maya.getUrlKey())) {
            throw ApiException.asApiException(HttpApiErrorCode.ILLEGAL_VALUE, "接口参数丢失");
        }
        String apiUrl = mayaApiConfig.getApiUrlValue(maya.getUrlKey());
        if (StringUtils.isBlank(apiUrl)) {
            throw ApiException.asApiException(HttpApiErrorCode.LOST_VALUE, "配置文件无法找到该接口信息");
        }
        Page iPage = new Page();
        ApiToken apiToken = ApiTokenUtil.defaultGetToken(mayaApiConfig.getTokenUrl(), mayaApiConfig.getAppKey(), mayaApiConfig.getSecretKey());
        if (null != apiToken && StringUtils.isNotBlank(apiToken.getAccessToken())) {
            Result result = choseRequest(maya.getMethod(), apiUrl, apiToken.getAccessToken(), maya.getMap(), page, REQUEST_FLAG_OTHER);
            iPage = analyzeJson(result, iPage, page);
            iPage = analyzeJson(result, iPage, page);
        } else {
            throw ApiException.asApiException(HttpApiErrorCode.TOKEN_EXCEPTION, "获取令牌异常");
        }
        return iPage;
    }

    @Override
    public Page<Map> restPage(RestParam restParam) {
        if (null == restParam || StringUtils.isBlank(restParam.getAppUrl())) {
            throw ApiException.asApiException(HttpApiErrorCode.ILLEGAL_VALUE, "接口参数丢失");
        }
        Page iPage = new Page();
        Page page = restParam.getPage();
        ApiToken apiToken = ApiTokenUtil.defaultGetToken(restParam.getTokenUrl(), restParam.getAppKey(), restParam.getAppSecret());
        if (null != apiToken && StringUtils.isNotBlank(apiToken.getAccessToken())) {
            Result result = choseRequest(restParam.getMethod(), restParam.getAppUrl(), apiToken.getAccessToken(), restParam.getMap(), page, REQUEST_FLAG_REST);
            iPage = analyzeJson(result, iPage, page);
        } else {
            throw ApiException.asApiException(HttpApiErrorCode.TOKEN_EXCEPTION, "获取令牌异常");
        }
        return iPage;
    }

    /**
     * 获取请求信息、根据对应的请求参数 提供post请求或者get 请求
     *
     * @param method     请求类型 支持 get 和post 请求 默认为post请求
     * @param appUrl     请求地址 获取请求数据的URL
     * @param token      令牌信息
     * @param searchMap  查询条件
     * @param searchPage 查询分页信息
     * @param flag       标识位 0 标识普通前端查询入口 1标识综合查询入口
     * @return
     */
    private Result choseRequest(String method, String appUrl, String token, Map<String, Object> searchMap, Page searchPage, String flag) {
        String type = StringUtils.isBlank(method) ? MethodType.POST.value() : method.toUpperCase();
        MethodType typeEnum = MethodType.getByValue(type);
        Result result;
        switch (typeEnum) {
            case GET:
                StringBuffer sb = new StringBuffer();
                String and = "&";
                if (null != searchMap && searchMap.size() > 0) {
                    searchMap.forEach((key, value) -> {
                        if (null != value) {
                            if (value.getClass().isArray()) {
                                sb.append(and).append(key).append("=").append(StringUtils.join((Object[]) value, ","));
                            } else {
                                sb.append(and).append(key).append("=").append(value.toString());
                            }
                        }
                    });
                }
                StringBuffer apiBuffer = new StringBuffer(appUrl);
                String why = "?";
                if (StringUtils.isNotBlank(sb.toString()) && sb.toString().contains(and)) {
                    if (appUrl.contains(why)) {
                        apiBuffer.append(sb.toString().trim());
                    } else {
                        apiBuffer.append(sb.toString().replaceFirst(and, "?").trim());
                    }
                }
                result = HttpClientUtil.get(new RequestParam(apiBuffer.toString(), token));
                break;
            default:
                Map<String, Object> postMap = new HashMap<>(16);
                postMap.put(JsonKeyConstant.MAYA_PARAMS_LIMIT, searchPage.getSize() + "");
                postMap.put(JsonKeyConstant.MAYA_PARAMS_OFFSET, ((searchPage.getCurrent() - 1) > 0 ? searchPage.getCurrent() - 1 : 0 + "") + "");
                if (REQUEST_FLAG_REST.equals(flag)) {
                    Object orderByKey = searchMap.get("orderByKey");
                    if (null != orderByKey) {
                        Map<String, String> mapOrderByKey = (Map<String, String>) orderByKey;
                        if (null != mapOrderByKey && mapOrderByKey.size() > 0) {
                            List<String> orderField = new ArrayList<>();
                            List<String> orderSort = new ArrayList<>();
                            mapOrderByKey.forEach((key, value) -> {
                                orderField.add(key);
                                orderSort.add(value);
                            });
                            String strOrderField = String.join(",", orderField);
                            String strOrderSort = String.join(",", orderSort);
                            postMap.put(JsonKeyConstant.MAYA_PARAMS_ORDER_FIELDS, StringUtils.isBlank(strOrderField) ? "" : strOrderField);
                            postMap.put(JsonKeyConstant.MAYA_PARAMS_ORDER_SORTS, StringUtils.isBlank(strOrderSort) ? "" : strOrderSort);
                        }
                    }
                    Object searchKey = searchMap.get("searchKey");
                    if (null != searchKey) {
                        searchMap = (Map<String, Object>) searchKey;
                    } else {
                        searchMap = null;
                    }
                }
                if (CollectionUtil.isNotEmpty(searchMap)) {
                    searchMap.forEach((key, value) -> {
                        if (null != value) {
                            if (value.getClass().isArray()) {
                                postMap.put(key, StringUtils.join((Object[]) value, ","));
                            } else {
                                postMap.put(key, value.toString().trim());
                            }
                        }
                    });
                }
                result = HttpClientUtil.post(new RequestParam(appUrl, token, postMap));
        }
        return result;
    }

    /**
     * 解析json 数据
     *
     * @param result     json返回值
     * @param resultPage 返回page数据
     * @param searchPage 查询参数 page数据
     * @return
     */
    private Page analyzeJson(Result result, Page resultPage, Page searchPage) {
        if (null != result && result.getCode() == HttpStatus.SC_OK) {
            String data = (String) result.getData();
            String jsonLeft = "[";
            if (StringUtils.isNotBlank(data) && data.startsWith(jsonLeft)) {
                List<Map> mapList = JsonMapper.fromJsonString(data, List.class);
                resultPage.setCurrent(searchPage.getCurrent());
                if (null != mapList) {
                    resultPage.setTotal((long) mapList.size());
                    resultPage.setRecords(mapList);
                }
            } else {
                JSONObject jsonObject = JSONUtil.parseObj(data);
                Boolean success = jsonObject.getBool(JsonKeyConstant.MAYA_RESULT_SUCCESS);
                if (null != success && success) {
                    JSONArray jsonArray = jsonObject.getJSONArray(JsonKeyConstant.MAYA_RESULT_DATA);
                    List<Map> mapList = JsonMapper.fromJsonString(jsonArray.toString(), List.class);
                    resultPage.setTotal(jsonObject.getLong(JsonKeyConstant.MAYA_RESULT_TOTAL));
                    resultPage.setCurrent(searchPage.getCurrent());
                    resultPage.setRecords(mapList);
                } else {
                    throw ApiException.asApiException(HttpApiErrorCode.API_EXCEPTION, jsonObject.getStr(JsonKeyConstant.MAYA_RESULT_MSG));
                }
            }
        } else {
            throw ApiException.asApiException(HttpApiErrorCode.HTTP_CLIENT_EXCEPTION, result.getMsg());
        }
        return resultPage;
    }

}
