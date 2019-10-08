package com.cloud.admin.api.annotation;

import java.lang.annotation.*;

/**
 * @author wengshij
 * @Date Created in 2019/3/7 9:56
 * Description: 日志实体注解、需要解析参数时使用到
 * Modified By:
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogField {
    /**
     * 解析该实体字段的名称
     */
  String title();
    /**
     * 需要翻译的字典类型
     * @return
     */
  String dictType() default "";


}
