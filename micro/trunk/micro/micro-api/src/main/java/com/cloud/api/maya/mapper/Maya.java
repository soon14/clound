package com.cloud.api.maya.mapper;

import lombok.Data;

import java.util.Map;

/**
 * @author wengshij
 * @date Created in 2019/8/10 15:26
 * @description:
 * @modified By:wengshij
 */
@Data
public class Maya {
    /**
     * 根据配置文件api URL地址接口的key值
     */
    String urlKey;
    /**
     * 方法调用类型
     * get post delete put等等方式
     */
    String method;
    /**
     * 查询条件拼接成map
     */
    Map<String, Object> map;
}
