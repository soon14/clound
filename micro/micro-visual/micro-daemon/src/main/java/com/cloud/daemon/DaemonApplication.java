

package com.cloud.daemon;

import com.cloud.common.job.annotation.EnableMicroJob;
import com.cloud.common.security.annotation.EnableMicroFeignClients;
import com.cloud.common.security.annotation.EnableMicroResourceServer;
import com.cloud.common.swagger.annotation.EnableMicroSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author ygnet
 * @date 2018/7/24
 * 分布式任务调度模块
 */
@EnableMicroJob
@EnableMicroSwagger2
@EnableMicroFeignClients
@SpringCloudApplication
@EnableMicroResourceServer
public class DaemonApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaemonApplication.class, args);
	}

}
