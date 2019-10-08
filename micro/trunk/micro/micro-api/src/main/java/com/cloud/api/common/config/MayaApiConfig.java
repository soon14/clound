package com.cloud.api.common.config;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.base.CaseFormat;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author wengshij
 * @date Created in 2019/8/10 11:40
 * @description:美亚API接口配置信息
 * @modified By:wengshij
 */

@Component
@RefreshScope
@Data
@ConfigurationProperties(prefix = "maya-api")
public class MayaApiConfig {
    /**
     * 获取token的URL
     */
    private String tokenUrl;
    /**
     * 获取token的凭证ID
     */
    private String appKey;
    /**
     * 获取凭证的密码
     */
    private String secretKey;
    /**
     * 所有接口信息
     */
    private Map<String, String> apiUrl;

    /**
     * 根据指定的KEY 值获取所有接口的代码值
     * 获取所有接口信息的URL
     *
     * @param key
     * @return
     */
    public String getApiUrlValue(String key) {
        String result = null;
        if (StringUtils.isNotBlank(key) && CollectionUtil.isNotEmpty(this.apiUrl)) {
            result = this.getApiUrl().get(key);
            if (StringUtils.isBlank(result)) {
                //驼峰形式的key值转换成 - 切割的代码值
                key = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, key);
                key = StringUtils.isBlank(key) ? key : StringUtils.replace(key, "_", "-");
                result = this.getApiUrl().get(key);
            }
        }
        return result;
    }

}
