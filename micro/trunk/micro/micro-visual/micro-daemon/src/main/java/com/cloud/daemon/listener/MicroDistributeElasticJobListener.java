

package com.cloud.daemon.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.AbstractDistributeOnceElasticJobListener;

/**
 * @author ygnet
 * @date 2018/7/24
 */
public class MicroDistributeElasticJobListener extends AbstractDistributeOnceElasticJobListener {

	public MicroDistributeElasticJobListener(long startedTimeoutMilliseconds, long completedTimeoutMilliseconds) {
		super(startedTimeoutMilliseconds, completedTimeoutMilliseconds);
	}

	@Override
	public void doBeforeJobExecutedAtLastStarted(ShardingContexts shardingContexts) {
		System.out.println(shardingContexts.getJobName() + " | MyDistributeElasticJobListener doBeforeJobExecutedAtLastStarted...");
	}

	@Override
	public void doAfterJobExecutedAtLastCompleted(ShardingContexts shardingContexts) {
		System.out.println(shardingContexts.getJobName() + " | MyDistributeElasticJobListener doAfterJobExecutedAtLastCompleted...");
	}
}
