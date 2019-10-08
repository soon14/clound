package com.cloud.api.common.entity;

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
public class RestParam implements Serializable {

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
     * rest 接口提供商
     * rest 接口提供商 每个种类的接口返回的json数据会有所不一样、
     * 所以需要为每个接口对应解析方式
     */
    String apiCompany;
}
