

package com.cloud.cas;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wengshij
 * @date Created in 2019/8/22 9:41
 * @description:CAS统一登录模块
 * @modified By:wengshij
 */
@SpringCloudApplication
public class CasApplication {

    public static void main(String[] args) {
        SpringApplication.run(CasApplication.class, args);
    }
}
