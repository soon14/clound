package com.cloud.admin.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author wuxx
 * @date 2019/2/21 14:53
 * @description: 字典树结构
 * @modified By:
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class DictTree extends TreeNode {

    private String label;
    private String type;
    private String value;
    private String description;
    private Integer sort;
    private String remarks;
    private String parentIds;
    private LocalDateTime updateTime;
}
