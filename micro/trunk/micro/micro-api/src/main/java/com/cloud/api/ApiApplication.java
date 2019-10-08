

package com.cloud.api;

import com.cloud.common.security.annotation.EnableMicroFeignClients;
import com.cloud.common.security.annotation.EnableMicroResourceServer;
import com.cloud.common.swagger.annotation.EnableMicroSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author wengshij
 * @date Created in 2019/8/8 14:53
 * @description: 接口微服务 、提供访问第三方rest 接口信息
 * @modified By:wengshij
 */
@EnableMicroSwagger2
@SpringCloudApplication
@EnableMicroFeignClients
@EnableMicroResourceServer
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
