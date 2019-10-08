

package com.cloud.monitor.support;

import com.cloud.common.core.constant.CommonConstants;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.eventstore.InMemoryEventStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author ygnet
 * @date 2018/11/12
 * <p>
 * redis store event
 * default 100
 */
@Slf4j
@Configuration
public class RedisEventStore extends InMemoryEventStore {
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public Mono<Void> append(List<InstanceEvent> events) {
		events.forEach(event -> {
			String key = event.getInstance().getValue() + "_" + event.getTimestamp().toString();
			log.info("保存实例事件的KEY：{},EVENT: {}", key, event.getType());
			redisTemplate.setKeySerializer(new StringRedisSerializer());
			redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(InstanceEvent.class));
			redisTemplate.opsForHash().put(CommonConstants.EVENT_KEY, key, event);
		});
		return super.append(events);
	}
}
