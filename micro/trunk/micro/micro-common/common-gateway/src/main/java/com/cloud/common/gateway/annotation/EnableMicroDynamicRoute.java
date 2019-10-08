

package com.cloud.common.gateway.annotation;

import com.cloud.common.gateway.configuration.DynamicRouteAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ygnet
 * @date 2018/11/5
 * <p>
 * 开启 动态路由
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(DynamicRouteAutoConfiguration.class)
public @interface EnableMicroDynamicRoute {
}
