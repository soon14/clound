

package com.cloud.daemon.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;

/**
 * @author ygnet
 * @date 2018/7/24
 * 任务监听器
 */
public class MicroElasticJobListener implements ElasticJobListener {

	@Override
	public void beforeJobExecuted(ShardingContexts shardingContexts) {
		System.out.println(shardingContexts.getJobName() + " | MyElasticJobListener beforeJobExecuted");
	}

	@Override
	public void afterJobExecuted(ShardingContexts shardingContexts) {
		System.out.println(shardingContexts.getJobName() + " | MyElasticJobListener afterJobExecuted");
	}
}
