package com.cloud.portal.common.thread;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author wengshij
 * @date Created in 2019/5/7 17:46
 * @description: 线程池参数信息
 * @modified By:wengshij
 */
@Component
@ConfigurationProperties(prefix = "thread-pool")
@RefreshScope
@Data
public class PoolParameter {

    /**
     * 核心线程数
     */
    private Integer corePoolSize;
    /**
     * 最大线程数
     */
    private Integer maxPoolSize;
    /**
     * 队列容量
     */
    private Integer queueCapacity;
    /**
     * 存活时间
     */
    private Integer aliveSeconds;
}
