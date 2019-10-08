package com.cloud.admin.api.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author wengshij
 * @date Created in 2019/8/8 16:09
 * @description:远程请求信息
 * @modified By:wengshij
 */
@Data
public class RestParamDTO implements Serializable {
    /**
     * 初始化默认值
     *
     * @param appUrl
     * @param appKey
     * @param appSecret
     * @param method
     * @param tokenUrl
     * @param apiCompany
     * @param map
     * @param page
     * @return
     */
    public static RestParamDTO initRest(String appUrl, String appKey, String appSecret, String method, String tokenUrl, String apiCompany, Map<String, Object> map, Page page) {
        RestParamDTO restParamDTO = new RestParamDTO();
        restParamDTO.setAppUrl(appUrl);
        restParamDTO.setAppKey(appKey);
        restParamDTO.setAppSecret(appSecret);
        restParamDTO.setMethod(method);
        restParamDTO.setTokenUrl(tokenUrl);
        restParamDTO.setApiCompany(apiCompany);
        restParamDTO.setMap(map);
        restParamDTO.setPage(page);
        return restParamDTO;
    }

    /**
     * 应用地址
     */
    String appUrl;
    /**
     * 应用key值
     */
    String appKey;
    /**
     * 应用秘钥
     */
    String appSecret;
    /**
     * 应用请求方式
     */
    String method;
    /**
     * 获取令牌地址URL
     */
    String tokenUrl;
    /**
     * 应用请求条件信息
     */
    Map<String, Object> map;
    /**
     * 返回结果分页信息
     */
    Page page;
    /**
     * api接口提供商
     * 识别每个接口信息 对应的参数设置和json 解析方法
     */
    String apiCompany;
}
