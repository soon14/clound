

package com.cloud.common.gateway.support;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.gateway.vo.RouteDefinitionVo;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ygnet
 * @date 2018/10/31
 * <p>
 * redis 保存路由信息，优先级比配置文件高
 */
@Slf4j
@Component
@AllArgsConstructor
public class RedisRouteDefinitionWriter implements RouteDefinitionRepository {
    private final RedisTemplate redisTemplate;
    /**
     * 暂时存放缓存信息、如果手动清空缓存、有备份路由信息
     */
    private static Map<String, List<RouteDefinitionVo>> cacheMap = Maps.newHashMap();

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(r -> {
            RouteDefinitionVo vo = new RouteDefinitionVo();
            BeanUtils.copyProperties(r, vo);
            log.info("保存路由信息{}", vo);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.opsForHash().put(CommonConstants.ROUTE_KEY, r.getId(), vo);
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        routeId.subscribe(id -> {
            log.info("删除路由信息{}", id);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.opsForHash().delete(CommonConstants.ROUTE_KEY, id);
        });
        return Mono.empty();
    }


    /**
     * 动态路由入口
     *
     * @return
     */
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinitionVo> values;
        try {
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouteDefinitionVo.class));
            values = redisTemplate.opsForHash().values(CommonConstants.ROUTE_KEY);
            if (CollectionUtil.isNotEmpty(values)) {
                cacheMap.put(CommonConstants.ROUTE_KEY, values);
            } else {
                if (CollectionUtil.isNotEmpty(cacheMap)) {
                    values = cacheMap.get(CommonConstants.ROUTE_KEY);
                    //重新放置到缓存中
                    values.forEach(route -> {
                        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouteDefinitionVo.class));
                        redisTemplate.opsForHash().put(CommonConstants.ROUTE_KEY, route.getId(), route);
                    });
                }
            }
        } catch (Exception e) {
            log.error("redis获取路由信息异常,ex={}", e.getMessage(), e);
            values = cacheMap.get(CommonConstants.ROUTE_KEY);
        }
        List<RouteDefinition> definitionList = new ArrayList<>();
        values.forEach(vo -> {
            RouteDefinition routeDefinition = new RouteDefinition();
            BeanUtils.copyProperties(vo, routeDefinition);
            definitionList.add(vo);
        });
        log.debug("redis 中路由定义条数： {}， {}", definitionList.size(), definitionList);
        return Flux.fromIterable(definitionList);
    }
}
