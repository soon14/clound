package com.cloud.portal.archive.tyyh.model;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author lvlinc
 * @date Created in 2019/6/18
 * @description:
 * @modified By:lvlinc
 */

@Component
@ConfigurationProperties(prefix = "tyyh")
@RefreshScope
@Data
public class TyyhParameter {

    private String orgurl;

    private String userurl;

    private String accesskey;

    private String secretkey;
}
