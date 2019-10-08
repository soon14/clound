package com.cloud.portal.common.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wengshij
 * @date Created in 2019/5/7 17:05
 * @description: 线程池配置
 * @modified By:wengshij
 */
@Configuration
public class ThreadPoolConfig {
    @Autowired
    private PoolParameter poolParameter;

    @Bean("threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(poolParameter.getCorePoolSize());
        // 设置最大线程数
        executor.setMaxPoolSize(poolParameter.getMaxPoolSize());
        // 设置队列容量
        executor.setQueueCapacity(poolParameter.getQueueCapacity());
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(poolParameter.getAliveSeconds());
        // 设置默认线程名称
        executor.setThreadNamePrefix("thread_");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }
}
