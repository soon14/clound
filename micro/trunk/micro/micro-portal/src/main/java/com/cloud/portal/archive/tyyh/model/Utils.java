package com.cloud.portal.archive.tyyh.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Utils
{
    private static final String DATA_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static <T> T toObject(String json, Class<T> valueType)
    {
        Gson gson = new GsonBuilder().setDateFormat(DATA_FORMAT).serializeNulls().create();
        return gson.fromJson(json, valueType);
    }

    public static String httpGetUser(String url,String headername,String headervalue) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";

        try {
            // 通过址默认配置创建一个httpClient实例
            httpClient = HttpClients.createDefault();
            // 创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(url);
            // 设置请求头信息，鉴权
            httpGet.setHeader(headername, headervalue);
            // 设置配置请求参数
            // 连接主机服务超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)
                    // 请求超时时间
                    .setConnectionRequestTimeout(35000)
                    // 数据读取超时时间
                    .setSocketTimeout(60000)
                    .build();
            // 为httpGet实例设置配置
            httpGet.setConfig(requestConfig);
            // 执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            // 通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
