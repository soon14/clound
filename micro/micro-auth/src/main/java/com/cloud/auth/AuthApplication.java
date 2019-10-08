

package com.cloud.auth;


import com.cloud.common.security.annotation.EnableMicroFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author ygnet
 * @date 2018年06月21日
 * 认证授权中心
 */
@SpringCloudApplication
@EnableMicroFeignClients
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
}
