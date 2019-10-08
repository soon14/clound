package com.cloud.portal.common.constant;

/**
 * @author wengshij
 * @date Created in 2019/8/6 8:54
 * @description:综合查询json key值
 * @modified By:wengshij
 */
public interface JsonKey {
    /**
     * 综合查询 前端配置 列项
     */
    String COLUMN = "column";
    /**
     * 字段属性
     */
    String COLUMN_PROP = "prop";
    /**
     * 字段中文名称
     */
    String COLUMN_LABEL = "label";
    /**
     * 查询条件连接关联符号
     */
    String COLUMN_SYMBOL = "searchSymbol";
    /**
     * 是否查询条件
     */
    String COLUMN_SEARCH = "search";
    /**
     * 是否排序字段
     */
    String COLUMN_ORDER_BY = "orderBy";
    /**
     * 权限控制字段
     */
    String DATA_SCOPE = "dataScope";
    /**
     * 排序 升序
     */
    String SORT_ASC = "ASC";
    /**
     * 排序 降序
     */
    String SORT_DESC = "DESC";

    /**
     * 字段类型（下拉框 树框 时间框等）
     */
    String COLUMN_LTYPE = "type";
    /**
     * 是否主键
     */
    String IS_PK ="isPk";
}
