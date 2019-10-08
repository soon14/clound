

package com.cloud.common.swagger.annotation;

import com.cloud.common.swagger.config.SwaggerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ygnet
 * @date 2018/7/21
 * 开启 swagger
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SwaggerAutoConfiguration.class})
public @interface EnableMicroSwagger2 {
}
