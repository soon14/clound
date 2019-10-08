

package com.cloud.codegen;

import com.cloud.common.security.annotation.EnableMicroResourceServer;
import com.cloud.common.security.annotation.EnableMicroFeignClients;
import com.cloud.common.swagger.annotation.EnableMicroSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author ygnet
 * @date 2018/07/29
 * 代码生成模块
 */
@EnableMicroSwagger2
@SpringCloudApplication
@EnableMicroFeignClients
@EnableMicroResourceServer
public class CodeGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeGenApplication.class, args);
	}
}
