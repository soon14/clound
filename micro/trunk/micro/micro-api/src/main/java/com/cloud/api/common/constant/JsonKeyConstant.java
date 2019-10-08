package com.cloud.api.common.constant;

/**
 * @author wengshij
 * @date Created in 2019/8/11 14:57
 * @description:Jsonkey值
 * @modified By:wengshij
 */
public interface JsonKeyConstant {
    /**
     * 美亚接口传递参数json key 值
     * 限制条数
     */
    String MAYA_PARAMS_LIMIT = "limit";
    /**
     * 美亚接口传递参数json key 值
     * 分页开始值
     */
    String MAYA_PARAMS_OFFSET = "offset";
    /**
     * 接口返回值 成功标识
     */
    String MAYA_RESULT_SUCCESS = "success";
    /**
     * 接口返回值 接口数据
     */
    String MAYA_RESULT_DATA = "data";
    /**
     * 接口返回值 接口总条数
     */
    String MAYA_RESULT_TOTAL = "total";
    /**
     * 接口返回值 异常信息
     */
    String MAYA_RESULT_MSG = "msg";

    /**
     * 排序字段
     */
    String MAYA_PARAMS_ORDER_FIELDS = "orderFields";
    /**
     * 和排序字段一起使用
     */
    String MAYA_PARAMS_ORDER_SORTS = "orderSorts";
    /**
     * 查询返回的字段内容
     */
    String MATA_PARAMS_RETURN_FIELDS = "fields";
}
