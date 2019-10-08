package com.cloud.common.core.http;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.core.http.entity.RequestParam;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wengshij
 * @date Created in 2019/8/8 16:21
 * @description:http client 请求方法
 * @modified By:wengshij
 */
@Slf4j
@UtilityClass
public class HttpClientUtil {

    private ThreadPoolExecutor asyncExecutor = RetryUtil.createThreadPoolExecutor();


    /**
     * 发送GET请求
     *
     * @param requestParam 请求信息地址
     * @return 返回数据
     */
    public Result get(RequestParam requestParam) {
        CloseableHttpClient httpClient = null;
        String msg;
        try {
            URIBuilder uriBuilder = new URIBuilder(requestParam.getUrl());
            httpClient = initApacheHttpClient(requestParam.getUrl());
            HttpGet get = new HttpGet(uriBuilder.build());
            if (CollectionUtil.isNotEmpty(requestParam.getRequestHeader())) {
                get.setHeaders(requestParam.getRequestHeader().stream().toArray(Header[]::new));
            }
            return executeRequest(httpClient, get, Boolean.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            msg = e.getMessage();
        } finally {
            close(httpClient);
        }
        return Result.failed(msg, CommonConstants.DEFAULT_ERROR_CODE);

    }

    /**
     * 发送 client http put 请求
     *
     * @param requestParam
     * @return
     */
    public Result put(RequestParam requestParam) {

        CloseableHttpClient httpClient = null;
        String msg;
        try {
            URIBuilder uriBuilder = new URIBuilder(requestParam.getUrl());
            HttpPut put = new HttpPut(uriBuilder.build());
            httpClient = initApacheHttpClient(requestParam.getUrl());
            if (CollectionUtil.isNotEmpty(requestParam.getRequestBody())) {
                put.setEntity(new UrlEncodedFormEntity(requestParam.getRequestBody(), CommonConstants.DEFAULT_CHARSET));
            }
            if (CollectionUtil.isNotEmpty(requestParam.getRequestHeader())) {
                put.setHeaders(requestParam.getRequestHeader().stream().toArray(Header[]::new));
            }
            if (CollectionUtil.isNotEmpty(requestParam.getRequestMapBody())) {
                String searchJsonMap = JSONUtil.toJsonStr(requestParam.getRequestMapBody());
                put.setEntity(new StringEntity(searchJsonMap, ContentType.APPLICATION_JSON));
            }
            return executeRequest(httpClient, put, Boolean.TRUE);
        } catch (Exception e) {
            log.error(e.getMessage());
            msg = e.getMessage();
            e.printStackTrace();
        } finally {
            close(httpClient);
        }
        return Result.failed(msg, CommonConstants.DEFAULT_ERROR_CODE);

    }

    /**
     * http client 删除请求方式
     *
     * @param requestParam
     * @return
     */
    public Result delete(RequestParam requestParam) {
        String msg;
        CloseableHttpClient httpClient = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(requestParam.getUrl());
            httpClient = initApacheHttpClient(requestParam.getUrl());
            HttpDelete del = new HttpDelete(uriBuilder.build());
            return executeRequest(httpClient, del, Boolean.TRUE);
        } catch (Exception e) {
            msg = e.getMessage();
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            close(httpClient);
        }
        return Result.failed(msg, CommonConstants.DEFAULT_ERROR_CODE);
    }


    /**
     * 发送POST请求，支持HTTP与HTTPS, 参数放入请求体方式
     *
     * @param requestParam
     * @return
     */
    public Result post(RequestParam requestParam) {
        String msg;
        CloseableHttpClient httpClient = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(requestParam.getUrl());
            HttpPost httpPost = new HttpPost(uriBuilder.build());
            httpClient = initApacheHttpClient(requestParam.getUrl());
            if (CollectionUtil.isNotEmpty(requestParam.getRequestHeader())) {
                httpPost.setHeaders(requestParam.getRequestHeader().stream().toArray(Header[]::new));
            }
            if (CollectionUtil.isNotEmpty(requestParam.getRequestBody())) {
                httpPost.setEntity(new UrlEncodedFormEntity(requestParam.getRequestBody(), CommonConstants.DEFAULT_CHARSET));

            }
            if (CollectionUtil.isNotEmpty(requestParam.getRequestMapBody())) {
                String searchJsonMap = JSONUtil.toJsonStr(requestParam.getRequestMapBody());
                httpPost.setEntity(new StringEntity(searchJsonMap, ContentType.APPLICATION_JSON));
            }

            return executeRequest(httpClient, httpPost, Boolean.TRUE);
        } catch (Exception e) {

            msg = e.getMessage();
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            close(httpClient);
        }
        return Result.failed(msg, CommonConstants.DEFAULT_ERROR_CODE);
    }


    /**
     * 执行请求http请求
     *
     * @param httpClient
     * @param httpRequestBase
     * @return
     * @throws Exception
     */
    public Result executeRequest(CloseableHttpClient httpClient, HttpRequestBase httpRequestBase, Boolean closeClient) throws Exception {
        HttpResponse response;
        Result result;
        try {
            response = httpClient.execute(httpRequestBase);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                log.error("请求地址：" + httpRequestBase.getURI() + ", 请求方法：" + httpRequestBase.getMethod()
                        + ",STATUS CODE = " + response.getStatusLine().getStatusCode());
                if (httpRequestBase != null) {
                    httpRequestBase.abort();
                }
                result = Result.failed(EntityUtils.toString((response).getEntity()), response.getStatusLine().getStatusCode());
            } else {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = Result.ok(EntityUtils.toString(entity, Consts.UTF_8));
                } else {
                    throw new Exception("Response Entity Is Null");
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (closeClient) {
                close(httpClient);
            }

        }
        return result;
    }


    /**
     * 创建链接
     */
    private CloseableHttpClient initApacheHttpClient(String url) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        CloseableHttpClient httpClient;
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(CommonConstants.HTTP_TIMEOUT_MILLION_SECONDS)
                .setConnectTimeout(CommonConstants.HTTP_TIMEOUT_MILLION_SECONDS).setConnectionRequestTimeout(CommonConstants.HTTP_TIMEOUT_MILLION_SECONDS)
                .setContentCompressionEnabled(true).build();
        if (StringUtils.isNotBlank(url) && url.toLowerCase().startsWith(CommonConstants.HTTPS_PROTOCOL)) {
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                    builder.build(), (s, sslSession) -> true);
            httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClientBuilder.create().setMaxConnTotal(CommonConstants.POOL_SIZE).setMaxConnPerRoute(CommonConstants.POOL_SIZE)
                    .setDefaultRequestConfig(requestConfig).build();
        }
        return httpClient;
    }

    /**
     * 关闭链接
     *
     * @param httpClient
     */
    private void close(CloseableHttpClient httpClient) {
        if (null != httpClient) {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public Result executeWithRetry(final CloseableHttpClient httpClient, final HttpRequestBase httpRequestBase, final int retryTimes, final long retryInterval) throws Exception {
        Result result = RetryUtil.asyncExecuteWithRetry(() ->
                executeRequest(httpClient, httpRequestBase, false), retryTimes, retryInterval, true, CommonConstants.HTTP_TIMEOUT_MILLION_SECONDS + 1000, asyncExecutor);
        close(httpClient);
        return result;
    }


}
