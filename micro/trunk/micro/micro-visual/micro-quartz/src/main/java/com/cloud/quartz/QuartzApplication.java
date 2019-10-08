package com.cloud.quartz;


import com.cloud.common.security.annotation.EnableMicroFeignClients;
import com.cloud.common.security.annotation.EnableMicroResourceServer;
import com.cloud.common.swagger.annotation.EnableMicroSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author wengshij
 * @date Created in 2019/6/25 9:51
 * @description: spring quart 定时任务
 * @modified By:wengshij
 */
@EnableMicroSwagger2
@SpringCloudApplication
@EnableMicroFeignClients
@EnableMicroResourceServer(details = true)
public class QuartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuartzApplication.class, args);
	}
}
