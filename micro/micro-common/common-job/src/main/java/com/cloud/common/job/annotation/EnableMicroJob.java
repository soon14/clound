

package com.cloud.common.job.annotation;

import com.cloud.common.job.ElasticJobAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ygnet
 * @date 2018/7/24
 * 开启micro job
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ElasticJobAutoConfiguration.class})
public @interface EnableMicroJob {
}
