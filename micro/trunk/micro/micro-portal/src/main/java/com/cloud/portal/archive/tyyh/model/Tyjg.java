package com.cloud.portal.archive.tyyh.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.cloud.admin.api.dto.TreeNode;
import com.cloud.portal.archive.common.base.model.DataModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019/6/10
 * @description:
 * @modified By:lvlinc
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class Tyjg extends TreeNode {
    private String id;
    private String pid;
    private String text;
    private String name;
    private String isLeaf;
    private String rootNodeId;
    private String isTreeNode;
    private String parentId;
}
