package com.cloud.portal.point.index.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wengshij
 * @date Created in 2019/7/17 15:37
 * @description:指标树结构信息
 * @modified By:wengshij
 */
@Data
public class IndexTree extends  PointIndex implements Serializable {
    /**
     * 指标名称或者场所类型名称
     */
    private String label;
    /**
     * 子级：指标值（指标udId+version）
     * 父级：场所类型字典值
     */
    private String value;
    /**
     * 是否父级
     */
    private boolean isParent;

    /**
     * 父级ID
     */
    private String parentId;
    /**
     * 子级
     */
    private List<Map<String,Object>> children = new ArrayList<>();

     private boolean isDisabled;






}
