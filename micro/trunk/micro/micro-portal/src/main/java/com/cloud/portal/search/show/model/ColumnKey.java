package com.cloud.portal.search.show.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wengshij
 * @date Created in 2019/8/6 10:29
 * @description:
 * @modified By:wengshij
 */
@Data
public class ColumnKey implements Serializable {
    /**
     * 真实名称
     */
    private String realName;
    /**
     * 修改后的名称
     */
    private String shamName;
    /**
     * 中文名称
     */
    private String cnName;
    /**
     * 查询条件关联符号 、例如  like eq gt lt 等等
     */
    private String symbol;
    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * 字段类型（下拉框 树框 时间框等）
     */
    private String type;

}
