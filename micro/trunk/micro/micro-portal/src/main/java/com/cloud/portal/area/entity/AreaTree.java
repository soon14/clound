package com.cloud.portal.area.entity;

import com.cloud.admin.api.dto.TreeNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ryt
 * @date Created in 2019/5/28 10:34
 * @description:
 * @modified By:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AreaTree extends TreeNode {

    private static final long serialVersionUID = 1L;

    /**
     * 区域编码
     */
    private String code;
    /**
     * 区域名称
     */
    private String name;
    /**
     * 全称
     */
    private String fullName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 区域类型（1：国家；2：省份、直辖市；3：地市；4：区县）
     */
    private String type;
    /**
     * 父级ids
     */
    private String parentIds;
    /**
     * 备注
     */
    private String remarks;
}
