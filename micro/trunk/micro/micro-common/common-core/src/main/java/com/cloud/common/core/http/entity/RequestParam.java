package com.cloud.common.core.http.entity;

import com.cloud.common.core.constant.CommonConstants;
import lombok.Data;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpHeaders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wengshij
 * @date Created in 2019/8/8 16:09
 * @description:请求参数信息
 * @modified By:wengshij
 */
@Data
public class RequestParam implements Serializable {
    public RequestParam(String url, List<NameValuePair> requestBody, List<Header> requestHeader) {
        this.url = url;
        this.requestBody = requestBody;
        this.requestHeader = requestHeader;
    }


    public RequestParam(String url, Map<String, Object> requestMapBody, List<Header> requestHeader) {
        this.url = url;
        this.requestMapBody = requestMapBody;
        this.requestHeader = requestHeader;
    }

    public RequestParam(String url, String token, Map<String, Object> searchMap) {
        this.url = url;
        List<Header> headerList = new ArrayList<>(2);
        headerList.add(new BasicHeader(HttpHeaders.AUTHORIZATION, CommonConstants.AUTHORIZATION_BEARER + token));
        headerList.add(new BasicHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString()));
        this.requestHeader = headerList;
        this.requestMapBody = searchMap;
    }

    public RequestParam(String url, String token) {
        this.url = url;
        List<Header> headerList = new ArrayList<>(2);
        headerList.add(new BasicHeader(HttpHeaders.AUTHORIZATION, CommonConstants.AUTHORIZATION_BEARER + token));
        headerList.add(new BasicHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString()));
        this.requestHeader = headerList;
    }

    public RequestParam(String url) {
        this.url = url;
    }

    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求体、包含验证凭证信息、验证密码信息
     * 主要对应类型是contentType application/x-www-form-urlencoded
     */
    private List<NameValuePair> requestBody;
    /**
     * 请求头
     */
    private List<Header> requestHeader;

    /**
     * 请求体 查询信息 封装为MAP json 格式
     * 主要对应类型是 contentType application/json
     */
    private Map<String, Object> requestMapBody;

}
