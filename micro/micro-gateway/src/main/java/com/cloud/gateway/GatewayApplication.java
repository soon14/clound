

package com.cloud.gateway;


import com.cloud.common.gateway.annotation.EnableMicroDynamicRoute;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author ygnet
 * @date 2018年06月21日
 * 网关应用
 */
@EnableMicroDynamicRoute
@SpringCloudApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
