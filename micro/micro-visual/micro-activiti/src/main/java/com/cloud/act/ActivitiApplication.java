

package com.cloud.act;

import com.cloud.common.security.annotation.EnableMicroFeignClients;
import com.cloud.common.security.annotation.EnableMicroResourceServer;
import com.cloud.common.swagger.annotation.EnableMicroSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author ygnet
 * @date 2018/9/25
 * 工作流管理模块
 */
@EnableMicroSwagger2
@EnableMicroFeignClients
@EnableMicroResourceServer
@SpringCloudApplication
public class ActivitiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivitiApplication.class, args);
	}

}
