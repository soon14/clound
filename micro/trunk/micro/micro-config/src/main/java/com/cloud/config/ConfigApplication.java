

package com.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author ygnet
 * @date 2018年06月22日
 * 配置中心
 */
@EnableConfigServer
@SpringCloudApplication
public class ConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
	}
}
