

package com.cloud.admin;


import com.cloud.common.security.annotation.EnableMicroFeignClients;
import com.cloud.common.security.annotation.EnableMicroResourceServer;
import com.cloud.common.swagger.annotation.EnableMicroSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author ygnet
 * @date 2018年06月21日
 * 用户统一管理系统
 */
@EnableMicroSwagger2
@SpringCloudApplication
@EnableMicroFeignClients
@EnableMicroResourceServer(details = true)
public class UpmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(UpmsApplication.class, args);
	}

}
